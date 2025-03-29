package uberto.backendgrupo72025.domain

import jakarta.persistence.*
import uberto.backendgrupo72025.repository.ItemRepo

import java.time.LocalDate

@Entity
@Table(name = "comentarios")
class Comentario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @OneToOne  // Consultar por el borrar y el mismo genere vacios registro pero solo muestra 1
    val viaje: Viaje = Viaje(),
    @Column()
    var estrellas: Int = 0,
    @Column
    var mensaje: String = "",
    @Column()
    var fecha : LocalDate = LocalDate.now(),
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