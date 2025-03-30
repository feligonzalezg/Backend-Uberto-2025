package uberto.backendgrupo72025.domain

import com.fasterxml.jackson.annotation.JsonFormat
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
    @Column(columnDefinition = "TIMESTAMP(0)")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val fechaInicio: LocalDateTime = LocalDateTime.now(),
    @Column(columnDefinition = "TIMESTAMP(0)")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val fechaFin: LocalDateTime = LocalDateTime.now(),
    @Column
    val cantidadDePasajeros: Int = 0,
    @Column
    val duracion: Int = 0,
    @Column
    var importe: Double = 0.0,
) {

    fun viajePendiente() = fechaFin.isAfter(LocalDateTime.now())

    fun viajeFinalizado()= !viajePendiente()

    fun seSolapan(fechaNueva: LocalDateTime, duracion: Int): Boolean {
        val nuevaFechaFin = fechaNueva.plusMinutes(duracion.toLong())
        return fechaNueva.isBefore(fechaFin) && nuevaFechaFin.isAfter(fechaInicio)
    }
}