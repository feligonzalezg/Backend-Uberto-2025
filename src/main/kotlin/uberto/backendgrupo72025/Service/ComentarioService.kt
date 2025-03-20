package uberto.backendgrupo72025.Service

import org.springframework.stereotype.Service
import uberto.backendgrupo72025.DTO.CalificacionDTO
import uberto.backendgrupo72025.DTO.toComentario
import uberto.backendgrupo72025.Domain.Comentario
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Repository.ComentarioRepository

@Service
class ComentarioService(
    val comentarioRepository: ComentarioRepository,
) {

    fun getComentarioById(idComentario: Long) = comentarioRepository.findById(idComentario)

    fun calificar(calificacion: CalificacionDTO, viaje: Viaje): Comentario {
        val comentario = calificacion.toComentario(viaje)
        comentarioRepository.save(comentario)
        return comentario
    }

    fun eliminarComentario(comentario: Comentario) {
        comentarioRepository.delete(comentario)
    }
}