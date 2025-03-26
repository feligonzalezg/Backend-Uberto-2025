package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Viaje(
//    var id: Long? = 0,
    val viajero: Viajero,
    val conductor: Conductor,
    val origen: String,
    val destino: String,
    val fechaInicio: LocalDateTime,
    val cantidadDePasajeros: Int,
    val duracion: Int,
    var importe: Double = 0.0,
): ItemRepo {
    override var id: Long = -1

    fun fechaFin(fechaInicio: LocalDateTime, duracion: Int) = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun viajePendiente() = fechaFin(fechaInicio, duracion).isAfter(LocalDateTime.now())

    fun viajeFinalizado()= !viajePendiente()

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        val nuevaFechaFin = fechaFin(fechaNueva, duracion)
        return fechaNueva.isBefore(fechaFin(fechaInicio, duracion)) && nuevaFechaFin.isAfter(fechaInicio)
    }
}