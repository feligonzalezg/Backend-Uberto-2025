package uberto.backendgrupo72025.dto

import uberto.backendgrupo72025.domain.Conductor
import kotlin.math.roundToInt

data class ConductorDTO(
    val id: Long,
    val nombreYApellido: String,
    val dominio: String,
    val modelo: String,
    val calificacion: Double,
    val importe: Double,
    val marca: String,
    val anio: Int,
    val foto: String,
    val esChofer: Boolean
)

fun Conductor.toConductorDTO(cantidadDePasajeros: Int, duracion: Int, calificacion: Double) = ConductorDTO(
        id = id,
        nombreYApellido = nombreYApellido(),
        dominio = vehiculo.dominio,
        marca = vehiculo.marca,
        modelo = vehiculo.modelo,
        anio = vehiculo.anio,
        calificacion = ((calificacion* 10.0).roundToInt() / 10.0),
        importe = importeViaje(cantidadDePasajeros, duracion),
        foto = foto,
        esChofer = esChofer
    )
