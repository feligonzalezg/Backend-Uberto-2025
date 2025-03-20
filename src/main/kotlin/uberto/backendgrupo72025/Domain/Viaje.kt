package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Viaje(
//    var id: Long? = 0,
    val idViajero: Long,
    val idConductor: Long,
    val origen: String,
    val destino: String,
    val fechaInicio: LocalDateTime,
    val cantidadDePasajeros: Int,
    val duracion: Int,
    var importe: Double = 0.0,
    var puedeCalificar: Boolean = false,
): ItemRepo {
    override var id: Long = -1

    fun fechaFin() = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun fechaFin(fecha: LocalDateTime, duracion: Int) = fecha.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun estaPendiente(): Boolean {
        return fechaFin() > LocalDateTime.now()
    }

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        val nuevaFechaFin = fechaFin(fechaNueva, duracion)
        return fechaNueva.isBefore(fechaFin()) && nuevaFechaFin.isAfter(fechaInicio)
    }

    fun puedeCalificar() = puedeCalificar

}