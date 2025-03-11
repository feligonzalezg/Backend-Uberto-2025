package uberto.backendgrupo72025.Domain

import Conductor
import Viajero
import java.time.LocalDateTime

class Viaje(
    val origen: String,
    val destino: String,
    val fecha: LocalDateTime,
    val cantidadDePasajeros: Int,
    var duracion: Int,
    val chofer: Conductor,
    private val comentarios: MutableList<Comentario> = mutableListOf()
) {

    fun duracionAleatoria() {
        duracion = (1..99).random()
    }

    fun costoDelViaje(): Double {
        return chofer.precioBaseDelViaje + duracion * chofer.vehiculo.tipoVehiculo.calculoExtra(this)
    }

    fun getComentarios(): List<Comentario> = comentarios.toList()

    fun agregarComentario(comentario: Comentario) {
        if (!comentarioValido(comentario)) throw Exception("No se puede calificar")
        comentarios.add(comentario)
    }

    private fun comentarioValido(comentario: Comentario): Boolean = comentario.autor is Viajero
}