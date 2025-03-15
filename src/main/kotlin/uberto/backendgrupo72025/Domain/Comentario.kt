package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Domain.Usuario

import java.time.LocalDate

class Comentario(
    val autor: Usuario,
    val viaje: Viaje,
    var puntaje: Int,
    var mensaje: String,
    var fecha : LocalDate = LocalDate.now(),
) {

    fun modificarComentario(nuevoMensaje: String) {
        if (nuevoMensaje.isBlank()) throw Exception("El comentario no puede estar vacio")
        mensaje = nuevoMensaje
        fecha = LocalDate.now()
    }

    fun modificarPuntaje(nuevoPuntaje: Int) {
        if (nuevoPuntaje < 0 || nuevoPuntaje > 5) throw Exception("El comentario no puede estar vacio")
        puntaje = nuevoPuntaje
        fecha = LocalDate.now()
    }
}