package uberto.backendgrupo72025.DTO

import java.time.LocalDateTime

data class BusquedaDTO (
    val fecha: LocalDateTime,
    val duracion: Int,
    val cantidadDePasajeros: Int
)