package uberto.backendgrupo72025.Domain

import Conductor
import Viajero
import java.time.LocalDateTime

class Viaje(
    val origen: String,
    val destino: String,
    val fecha: LocalDateTime,
    val cantidadDePasajeros: Int,
    val duracion: Int = (1..99).random(),
    val chofer: Conductor
) {

    fun costoDelViaje(): Double {
        return chofer.precioBaseDelViaje + duracion * chofer.vehiculo.calculoPlusPorTipoVehiculo(this)
    }
}