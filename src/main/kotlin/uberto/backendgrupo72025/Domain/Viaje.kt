package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Viaje(
//    var id: Long? = 0,
    val origen: String,
    val destino: String,
    val fechaInicio: LocalDateTime = LocalDateTime.now(),
    val cantidadDePasajeros: Int,
    val duracion: Int,
    val fechaFin: LocalDateTime = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)
): ItemRepo {
    override var id: Long = -1

    fun costoDelViaje(chofer: Conductor): Double {
        return chofer.precioBaseDelViaje + duracion * chofer.vehiculo.calculoPlusPorTipoVehiculo(this)
    }

    fun estaPendiente(fechaActual: LocalDateTime): Boolean {
        return fechaFin > fechaActual
    }

//    fun seSolapan(viajeExistente: Viaje, nuevoViaje: Viaje): Boolean {
//        return viajeExistente.fechaInicio <= nuevoViaje.fechaFin &&
//                nuevoViaje.fechaInicio <= viajeExistente.fechaFin
//    }  val viajesPendientes = chofer.viajes.filter { it.estaPendiente(fechaActual) }
}