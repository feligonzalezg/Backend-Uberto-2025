package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Viaje
import java.time.LocalDateTime


data class ViajeDTO(
    val idViajero: Long,
    val idConductor: Long,
    val origen: String,
    val destino: String,
    val fecha: LocalDateTime,
    val cantidadDePasajeros: Int,
    val duracion: Int)

fun ViajeDTO.toViaje() = Viaje(
    origen = origen,
    destino =  destino,
    fecha = fecha,
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
)