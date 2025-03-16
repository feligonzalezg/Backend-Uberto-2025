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
): ItemRepo {
    override var id: Long = -1

    fun fechaFin() = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun fechaFin(fecha: LocalDateTime, duracion: Int) = fecha.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun costoDelViaje(chofer: Conductor): Double {
        return chofer.precioBaseDelViaje + duracion * chofer.vehiculo.calculoPlusPorTipoVehiculo(this)
    }

    fun estaPendiente(): Boolean {
        return fechaFin() > LocalDateTime.now()
    }

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        return fechaInicio > fechaFin(fechaNueva, duracion) &&
                fechaNueva > fechaFin()
    }

}