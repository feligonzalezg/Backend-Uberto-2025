package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor

data class ConductorDTO(
    val id: Long,
    val nombreYApellido: String,
    val patente: String,
    val movil: String,
    val calificacion: Double,
    val importe: Double,
    val marca: String,
    val modelo: Int
)


fun Conductor.toConductorDTO(cantidadDePasajeros: Int, duracion: Int) =  ConductorDTO(
    id = this.id,
    nombreYApellido = "$nombre $apellido",
    patente = vehiculo.patente,
    movil = "${vehiculo.marca} | ${vehiculo.modelo}",
    calificacion = calificacion(),
    importe = importeViaje(cantidadDePasajeros = 0, duracion = 0),
    marca = vehiculo.marca,
    modelo = vehiculo.anio,
)