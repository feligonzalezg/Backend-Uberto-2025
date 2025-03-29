package uberto.backendgrupo72025.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Entity
@Table(name = "viaje")
class Viaje(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "viajero_id")
    val viajero: Viajero = Viajero(),

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "conductor_id")
    val conductor: Conductor = Simple(),
    @Column
    val origen: String = "",
    @Column
    val destino: String = "",
    @Column
    val fechaInicio: LocalDateTime = LocalDateTime.now(),
    @Column
    val cantidadDePasajeros: Int = 0,
    @Column
    val duracion: Int = 0,
    @Column
    var importe: Double = 0.0,
) {


    fun fechaFin(fechaInicio: LocalDateTime, duracion: Int) = fechaInicio.plus(duracion.toLong(), ChronoUnit.MINUTES)

    fun viajePendiente() = fechaFin(fechaInicio, duracion).isAfter(LocalDateTime.now())

    fun viajeFinalizado()= !viajePendiente()

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        val nuevaFechaFin = fechaFin(fechaNueva, duracion)
        return fechaNueva.isBefore(fechaFin(fechaInicio, duracion)) && nuevaFechaFin.isAfter(fechaInicio)
    }
}