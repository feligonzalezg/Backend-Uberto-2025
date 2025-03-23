package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Service.ComentarioService

data class ConductorDTO(
    val idConductor: Long,
    val nombreYApellido: String,
    val patente: String,
    val movil: String,
    val calificacion: Double,
    val importe: Double,
    val marca: String,
    val modelo: Int
)


fun Conductor.toConductorDTO(cantidadDePasajeros: Int, duracion: Int, calificacion: Double) = ConductorDTO(
        idConductor = id,
        nombreYApellido = "$nombre $apellido",
        patente = vehiculo.patente,
        movil = "${vehiculo.marca} | ${vehiculo.modelo}",
        calificacion = calificacion,
        importe = importeViaje(cantidadDePasajeros, duracion),
        marca = vehiculo.marca,
        modelo = vehiculo.anio,
    )