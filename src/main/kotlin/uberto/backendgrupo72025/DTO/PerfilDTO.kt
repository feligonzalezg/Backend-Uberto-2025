package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Usuario
import uberto.backendgrupo72025.Domain.Viajero

open class PerfilDTO(
     val id: Long,
     val nombre: String,
     val apellido: String,
     val esChofer: Boolean,
     val comentarios: List<ComentarioDTO>
)

class PerfilViajeroDTO(
    id: Long,
    nombre: String,
    apellido: String,
    esChofer: Boolean,
    comentarios: List<ComentarioDTO>,
    val telefono: Int,
    val saldo: Double,
    val amigos: List<AmigoDTO>
): PerfilDTO(id, nombre, apellido, esChofer, comentarios)

fun Viajero.toPerfilDTO() = PerfilViajeroDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    comentarios = comentarios.map { it.toComentarioDTO() },
    telefono = telefono,
    saldo = saldo,
    amigos = amigos.map { it.toAmigoDTO() }
)

class PerfilChoferDTO(
    id: Long,
    nombre: String,
    apellido: String,
    esChofer: Boolean,
    comentarios: List<ComentarioDTO>,
    val precioBase: Double,
    val dominio: String,
    val descripcion: String,
    val modelo: String
): PerfilDTO(id, nombre, apellido, esChofer, comentarios)

fun Conductor.toPerfilDTO() = PerfilChoferDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    comentarios = comentarios.map { it.toComentarioDTO() },
    precioBase = precioBaseDelViaje,
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

data class AmigoDTO(
    val nombreYApellido : String,
    val username: String
)

fun Usuario.toAmigoDTO() = AmigoDTO(
    nombreYApellido = "$nombre $apellido",
    username = username
)