package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Usuario
import uberto.backendgrupo72025.Domain.Viajero

data class PerfilViajeroDTO(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val telefono: Int,
    val esChofer: Boolean,
    val saldo: Double
)

fun Viajero.toPerfilDTO() = PerfilViajeroDTO(
    id = id,
    nombre = nombreYApellido,
    apellido = nombreYApellido,
    telefono = telefono,
    esChofer = esChofer,
    saldo = saldo
)

data class PerfilChoferDTO(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val precioBase: Double,
    val esChofer: Boolean,
    val dominio: String,
    val descripcion: String,
    val modelo: String
)

fun Conductor.toPerfilDTO() = PerfilChoferDTO(
    id = id,
    nombre = nombreYApellido,
    apellido = nombreYApellido,
    precioBase = precioBaseDelViaje,
    esChofer = esChofer,
    dominio = vehiculo.patente,
    descripcion = vehiculo.marca,
    modelo = vehiculo.modelo
)

data class LoginDTO(
    val id : Long,
    val esChofer: Boolean
)

fun Usuario.toDTO1() = LoginDTO(
    id =  id,
    esChofer = esChofer
    )