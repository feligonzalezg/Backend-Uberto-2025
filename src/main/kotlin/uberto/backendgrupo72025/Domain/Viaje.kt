package uberto.backendgrupo72025.Domain

import Conductor
import java.time.LocalDateTime

class Viaje(
    val origen : String,
    val destino : String,
    val fecha : LocalDateTime,
    val cantidadDePasajeros : Int,
    var duracion : Int,
    val chofer : Conductor
    ) {

    fun duracionAleatoria() {
        duracion = (1..99).random()
    }

    fun costoDelViaje(): Double {
        return chofer.precioBaseDelViaje + duracion * chofer.vehiculo.tipoVehiculo.calculoExtra(this)
    }
}