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
): ItemRepo {
    override var id: Long = -1

    var tieneComentario: Boolean = false

    fun fechaFin(fechaInicio: LocalDateTime, duracion: Int) = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun estaPendiente() = fechaFin(fechaInicio, duracion).isAfter(LocalDateTime.now())

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        val nuevaFechaFin = fechaFin(fechaNueva, duracion)
        return fechaNueva.isBefore(fechaFin(fechaInicio, duracion)) && nuevaFechaFin.isAfter(fechaInicio)
    }

    fun puedeCalificar() = !estaPendiente() && !tieneComentario

    //  fun viajeFinalizado()= fechaFin()< LocalDateTime.now()

}