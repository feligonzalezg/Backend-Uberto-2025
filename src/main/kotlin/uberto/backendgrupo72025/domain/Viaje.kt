package uberto.backendgrupo72025.domain

import Conductor
import java.time.LocalDateTime

class Viaje(
    val origen : String,
    val destino : String,
    val fecha : LocalDateTime,
    val cantidadDePasajeros : Int,
    val duracion : Int,
    val chofer : Conductor
    ) {

    fun duracionAleatoria(): Int {
        return (1..99).random()
    }
}