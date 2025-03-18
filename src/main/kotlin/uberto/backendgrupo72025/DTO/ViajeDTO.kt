package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
    idConductor = idConductor,
    origen = origen,
    destino =  destino,
    fechaInicio = fechaInicio,
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe
)

//data class ViajesDTO(
//    val viajesRealizados: List<ViajePerfilDTO>
//    val viajesPendientes: List<ViajePerfilDTO>,
//)

data class ViajeCardDTO(
    val conductor: String,
    val origen: String,
    val destino: String,
    val fechaInicio: String,
    val cantidadDePasajeros: Int,
    val importe: Double
)

fun Viaje.toViajeCardDTO(conductor: Conductor) = ViajeCardDTO(
    conductor = "${conductor.nombre} ${conductor.apellido}",
    origen = origen,
    destino = destino,
    fechaInicio = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    cantidadDePasajeros = cantidadDePasajeros,
    importe = importe
)

