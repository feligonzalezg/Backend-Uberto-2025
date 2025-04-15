package uberto.backendgrupo72025.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.dto.CalificacionDTO
import uberto.backendgrupo72025.dto.ComentarioDTO
import uberto.backendgrupo72025.dto.toComentario
import uberto.backendgrupo72025.dto.toComentarioDTO
import uberto.backendgrupo72025.domain.*
import uberto.backendgrupo72025.repository.ComentarioRepository

@Service
class ComentarioService(
    val comentarioRepository: ComentarioRepository
) {

    fun getAll() = comentarioRepository.findAll()

    fun getComentarioById(idComentario: Long) = comentarioRepository.findById(idComentario)

    fun getComentariosByViajeroId(idUsuario: Long) = getAll().filter { it.viaje.viajero.id == idUsuario }

    fun getComentariosByConductorId(idUsuario: Long) = getAll().filter { it.viaje.conductor.id == idUsuario }

    fun getComentarios(idUsuario: Long, esChofer: Boolean): List<ComentarioDTO> {
        return if (esChofer) {
            getComentariosByConductorId(idUsuario).map { it.toComentarioDTO(it.viaje.viajero.nombreYApellido(), it.viaje.viajero.foto) }
        } else {
            getComentariosByViajeroId(idUsuario).map { it.toComentarioDTO(it.viaje.conductor.nombreYApellido(), it.viaje.conductor.foto) }
        }
    }

    fun calificar(calificacion: CalificacionDTO, viaje: Viaje, idUsuario: Long): Comentario {
        validarPuedeCalificar(idUsuario, viaje)
        val comentario = calificacion.toComentario(viaje)
        comentarioRepository.save(comentario)
        return comentario
    }

    fun validarPuedeCalificar(idUsuario: Long, viaje: Viaje) {
        validarEsElViajero(idUsuario, viaje)
        validarNoCalificado(idUsuario, viaje)
    }

    fun validarEsElViajero(idUsuario: Long, viaje: Viaje) {
        if (idUsuario != viaje.viajero.id) throw BadRequestException("No se puede calificar un viaje en el que no participaste.")
    }

    fun validarNoCalificado(idUsuario: Long, viaje: Viaje) {
        if (getComentariosByViajeroId(idUsuario).any { it.viaje.id == viaje.id }) throw BadRequestException("No se puede calificar el mismo viaje más de una vez.")
    }

    @Transactional
    fun eliminarComentario(idViajero: Long, idComentario: Long) {
        val comentario = getComentarioById(idComentario)
        validarEliminarComentario(idViajero, comentario)
        comentarioRepository.delete(comentario)
    }

    fun validarEliminarComentario(idViajero: Long, comentario: Comentario) {
        if (idViajero != comentario.viaje.viajero.id) throw BadRequestException("No se puede eliminar un comentario realizado por otro usuario")
    }

    fun getCalificacionByConductor(idConductor: Long): Double {
        val cantidadComentarios = cantidadComentariosByConductor(idConductor)
        return if (cantidadComentarios > 0)
            puntajeTotal(idConductor) / cantidadComentarios.toDouble()
        else 0.0
    }

    fun cantidadComentariosByConductor(idConductor: Long) = getComentariosByConductorId(idConductor).size

    fun puntajeTotal(idConductor: Long) = getComentariosByConductorId(idConductor).sumOf { it.estrellas }

    fun viajeCalificado(idViaje: Long) = getAll().any { it.viaje.id  == idViaje }
}