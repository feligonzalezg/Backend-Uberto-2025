package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Service.ComentarioService
import kotlin.math.roundToInt

data class ConductorDTO(
    val idConductor: Long,
    val nombreYApellido: String,
    val dominio: String,
    val modelo: String,
    val calificacion: Double,
    val importe: Double,
    val marca: String,
    val anio: Int,
    val foto: String
)

fun Conductor.toConductorDTO(cantidadDePasajeros: Int, duracion: Int, calificacion: Double) = ConductorDTO(
        idConductor = id,
        nombreYApellido = nombreYApellido(),
        dominio = vehiculo.dominio,
        marca = vehiculo.marca,
        modelo = vehiculo.modelo,
        anio = vehiculo.anio,
        calificacion = ((calificacion* 10.0).roundToInt() / 10.0),
        importe = importeViaje(cantidadDePasajeros, duracion),
        foto = foto
    )
