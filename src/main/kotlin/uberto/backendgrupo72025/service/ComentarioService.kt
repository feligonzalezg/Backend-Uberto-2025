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

    fun getComentarioById(idComentario: String?) = idComentario?.let { comentarioRepository.findById(it).orElseThrow{ NotFoundException("No se encontro el comentario con id $it") } }!!

    fun getComentarios(idUsuario: String, esChofer: Boolean): List<ComentarioDTO> {
        return if (esChofer) {
            comentarioRepository.findByViajeConductorIdAndActive(idUsuario).map { it.toComentarioDTO(it.viaje.viajero.nombreYApellido(), it.viaje.viajero.foto) }
        } else {
            comentarioRepository.findByViajeViajeroIdAndActive(idUsuario).map { it.toComentarioDTO(it.viaje.conductor.nombreYApellido(), it.viaje.conductor.foto) }
        }
    }

    fun calificar(calificacion: CalificacionDTO, viaje: Viaje, idUsuario: String?): Comentario {
        validarPuedeCalificar(idUsuario, viaje)
        val comentario = calificacion.toComentario(viaje)
        comentarioRepository.save(comentario)
        return comentario
    }

    fun validarPuedeCalificar(idUsuario: String?, viaje: Viaje) {
        validarEsElViajero(idUsuario, viaje)
        validarNoCalificado(idUsuario, viaje)
    }

    fun validarEsElViajero(idUsuario: String?, viaje: Viaje) {
        if (idUsuario != viaje.viajero.id) throw BadRequestException("No se puede calificar un viaje en el que no participaste.")
    }

    fun validarNoCalificado(idUsuario: String?, viaje: Viaje) {
        if (viajeCalificado(viaje.id)) throw BadRequestException("No se puede calificar el mismo viaje más de una vez.")
    }

    fun viajeCalificado(idViaje : String) = comentarioRepository.existsByViajeIdAndActive(idViaje, true)

    fun eliminarComentario(idViajero: String?, comentario: Comentario) {
        validarEliminarComentario(idViajero, comentario)
        comentario.active = false
        comentarioRepository.save(comentario)
    }

    fun validarEliminarComentario(idViajero: String?, comentario: Comentario) {
        if (idViajero != comentario.viaje.viajero.id) throw BadRequestException("No se puede eliminar un comentario realizado por otro usuario")
    }

    fun getCalificacionByConductor(idConductor: String?) = comentarioRepository.promedioEstrellasByConductor(idConductor)

}