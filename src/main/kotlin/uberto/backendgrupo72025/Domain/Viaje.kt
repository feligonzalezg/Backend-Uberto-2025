package uberto.backendgrupo72025.Domain

import java.time.LocalDateTime

class Viaje(
//    var id: Long? = 0,
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