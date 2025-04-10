package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Repository.ItemRepo

import java.time.LocalDate

class Comentario(
    val viaje: Viaje,
    var estrellas: Int,
    var mensaje: String,
    var fecha : LocalDate = LocalDate.now(),
): ItemRepo {
    override var id: Long = -1

    fun modificarComentario(nuevoMensaje: String) {
        if (nuevoMensaje.isBlank()) throw BadRequestException("El comentario no puede estar vacio")
        mensaje = nuevoMensaje
        fecha = LocalDate.now()
    }

    fun modificarPuntaje(nuevoPuntaje: Int) {
        if (nuevoPuntaje < 0 || nuevoPuntaje > 5) throw BadRequestException("El comentario no puede estar vacio")
        estrellas = nuevoPuntaje
        fecha = LocalDate.now()
    }
}