package uberto.backendgrupo72025.Domain

import Usuario
import java.time.LocalDateTime

class Comentario(
    val autor: Usuario,
    val viaje: Viaje,
    private var puntaje: Int,
    private var mensaje: String
) {

    fun puntaje(): Int = puntaje

    fun mensaje(): String = mensaje

    fun modificarComentario(nuevoMensaje: String) {
        if (nuevoMensaje.isBlank()) throw Exception("El comentario no puede estar vacio")
        mensaje = nuevoMensaje
    }

    fun modificarPuntaje(nuevoPuntaje: Int) {
        if (nuevoPuntaje < 0 || nuevoPuntaje > 5) throw Exception("El comentario no puede estar vacio")
        puntaje = nuevoPuntaje
    }
}