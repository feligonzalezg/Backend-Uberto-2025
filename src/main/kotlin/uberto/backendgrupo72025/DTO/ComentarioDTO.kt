package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Comentario
import java.time.LocalDate

data class ComentarioDTO(
    val autor: String,
    val puntaje: Int,
    val mensaje: String,
    val fecha: LocalDate
)

fun Comentario.toComentarioDTO() = ComentarioDTO(
    autor = autor.nombre + " " + autor.apellido,
    puntaje = puntaje,
    mensaje = mensaje,
    fecha = fecha
)
