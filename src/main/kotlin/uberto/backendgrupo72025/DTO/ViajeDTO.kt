package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class ViajeDTO(
    val id: Long,
    val idViajero: Long,
    val idConductor: Long,
    var nombre: String,
    val origen: String,
    val destino: String,
    val fechaInicio: String,
    val cantidadDePasajeros: Int,
    val duracion: Int,
    val importe: Double,
    val puedeCalificar: Boolean,
)

fun ViajeDTO.toViaje() = Viaje(
    idViajero = idViajero,
    idConductor = idConductor,
    origen = origen,
    destino =  destino,
    fechaInicio = LocalDateTime.parse(fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe
)

fun Viaje.toViajeDTO(nombre: String) = ViajeDTO(
    id = id,
    idViajero = idViajero,
    idConductor = idConductor,
    nombre = nombre,
    origen = origen,
    destino =  destino,
    fechaInicio = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe,
    puedeCalificar = puedeCalificar(),
)

//data class ViajesDTO(
//    val viajesRealizados: List<ViajePerfilDTO>
//    val viajesPendientes: List<ViajePerfilDTO>,
//)
//
//data class ViajeCardDTO(
//    val id: Long,
//    val conductor: String,
//    val viajero: String,
//    val origen: String,
//    val destino: String,
//    val fechaInicio: String,
//    val cantidadDePasajeros: Int,
//    val importe: Double
//)
//
//fun Viaje.toViajeCardDTO(conductor: Conductor) = ViajeCardDTO(
//    id = id,
//    conductor = "${conductor.nombre} ${conductor.apellido}",
//    viajero = "${conductor.nombre} ${conductor.apellido}",
//    origen = origen,
//    destino = destino,
//    fechaInicio = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
//    cantidadDePasajeros = cantidadDePasajeros,
//    importe = importe
//)

