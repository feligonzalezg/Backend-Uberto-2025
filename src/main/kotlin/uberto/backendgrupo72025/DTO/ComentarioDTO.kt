package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Comentario
import uberto.backendgrupo72025.Domain.Viaje
import java.time.format.DateTimeFormatter

data class ComentarioDTO(
    val idComentario: Long,
    val idViaje: Long,
    val nombre: String,
    val estrellas: Int,
    val mensaje: String,
    val fecha: String,
    val foto : String
)

fun Comentario.toComentarioDTO(nombre: String, foto : String) = ComentarioDTO(
    idComentario = id,
    idViaje = viaje.id,
    nombre = nombre,
    estrellas = estrellas,
    mensaje = mensaje,
    fecha = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    foto = foto
)

data class CalificacionDTO(
    val idViaje: Long,
    val estrellas: Int,
    val mensaje: String
)

fun CalificacionDTO.toComentario(viaje: Viaje) = Comentario(
    viaje = viaje,
    estrellas = estrellas,
    mensaje = mensaje
)