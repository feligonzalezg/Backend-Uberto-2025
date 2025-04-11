package uberto.backendgrupo72025.domain

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "comentarios")
class Comentario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = "",
    @ManyToOne(fetch = FetchType.LAZY)
    val viaje: Viaje = Viaje(),
    @Column
    var estrellas: Int = 0,
    @Column
    var mensaje: String = "",
    @Column
    var fecha : LocalDate = LocalDate.now(),
    @Column
    var active: Boolean = true
) {


    fun modificarComentario(nuevoMensaje: String) {
        if (nuevoMensaje.isBlank()) throw BadRequestException("El comentario no puede estar vacio")
        mensaje = nuevoMensaje
        fecha = LocalDate.now()
    }

    fun modificarPuntaje(nuevoPuntaje: Int) {
        if (nuevoPuntaje < 0 || nuevoPuntaje > 5) throw BadRequestException("El comentario no puede estar vacio")
        estrellas = nuevoPuntaje
        fecha = LocalDate.now()
    }
}