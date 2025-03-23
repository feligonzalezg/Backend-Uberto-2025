package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Domain.Viajero
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
    val fechaFin: String,
)

fun ViajeDTO.toViaje(viajero: Viajero, conductor: Conductor) = Viaje(
    viajero = viajero,
    conductor = conductor,
    origen = origen,
    destino =  destino,
    fechaInicio = LocalDateTime.parse(fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe
)

fun Viaje.toViajeDTO(nombre: String) = ViajeDTO(
    id = id,
    idViajero = viajero.id,
    idConductor = conductor.id,
    nombre = nombre,
    origen = origen,
    destino =  destino,
    fechaInicio = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    cantidadDePasajeros = cantidadDePasajeros,
    duracion = duracion,
    importe = importe,
    puedeCalificar = puedeCalificar(),
    fechaFin = fechaFin(fechaInicio, duracion).format(DateTimeFormatter.ofPattern("HH:mm")),
)