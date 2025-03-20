package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Comentario
import uberto.backendgrupo72025.Domain.Viaje
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ComentarioDTO(
    val id: Long,
    val idViaje: Long,
    val nombre: String,
    val puntaje: Int,
    val mensaje: String,
    val fecha: String
)

fun Comentario.toComentarioDTO(nombre: String) = ComentarioDTO(
    id = id,
    idViaje = viaje.id,
    nombre = nombre,
    puntaje = puntaje,
    mensaje = mensaje,
    fecha = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
)

data class CalificacionDTO(
    val idViaje: Long,
    val puntaje: Int,
    val mensaje: String
)

fun CalificacionDTO.toComentario(viaje: Viaje) = Comentario(
    viaje = viaje,
    puntaje = puntaje,
    mensaje = mensaje
)