package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import java.time.LocalDateTime


data class ViajeDTO(
    val idViajero: Long,
    val idConductor: Long,
    val origen: String,
    val destino: String,
    val fechaInicio: LocalDateTime,
    val cantidadDePasajeros: Int,
    val duracion: Int,
    val importe: Double,
)

fun ViajeDTO.toViaje() = Viaje(
    origen = origen,
    destino =  destino,
    fechaInicio = fechaInicio,
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe
)

data class ViajePerfilDTO(
    val conductor: AmigoDTO,
    val origen: String,
    val destino: String,
    val fechaInicio: LocalDateTime,
    val cantidadDePasajeros: Int,
    val importe: Double
)

fun Viaje.toViajePerfilDTO(conductor: Conductor) = ViajePerfilDTO(
    conductor = conductor.toAmigoDTO(),
    origen = origen,
    destino = destino,
    fechaInicio = fechaInicio,
    cantidadDePasajeros = cantidadDePasajeros,
    importe = importe
)