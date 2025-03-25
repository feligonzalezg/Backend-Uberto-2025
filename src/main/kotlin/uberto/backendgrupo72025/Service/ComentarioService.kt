package uberto.backendgrupo72025.Service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.CalificacionDTO
import uberto.backendgrupo72025.DTO.ComentarioDTO
import uberto.backendgrupo72025.DTO.toComentario
import uberto.backendgrupo72025.DTO.toComentarioDTO
import uberto.backendgrupo72025.Domain.*
import uberto.backendgrupo72025.Repository.ComentarioRepository

@Service
class ComentarioService(
    val comentarioRepository: ComentarioRepository,
    val viajeService: ViajeService
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

    fun calificar(calificacion: CalificacionDTO, viaje: Viaje): Comentario {
        val comentario = calificacion.toComentario(viaje)
        comentarioRepository.save(comentario)
        return comentario
    }

    @Transactional
    fun calificarViaje(idUsuario: Long, calificacion: CalificacionDTO): ComentarioDTO {
        val viaje = viajeService.getViajeById(calificacion.idViaje)
        validarPuedeCalificar(idUsuario, viaje)
        val comentario = calificar(calificacion, viaje)
        viaje.tieneComentario = true
        viajeService.updateViaje(viaje)
        return comentario.toComentarioDTO(viaje.conductor.nombreYApellido(), viaje.conductor.foto)
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
        comentario.viaje.tieneComentario = false
        viajeService.updateViaje(comentario.viaje)
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
}