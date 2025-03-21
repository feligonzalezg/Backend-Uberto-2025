package uberto.backendgrupo72025.DTO

import uberto.backendgrupo72025.Domain.*

interface PerfilDTO {
    val id: Long
    val nombre: String
    val apellido: String
    val esChofer: Boolean
}

data class PerfilViajeroDTO(
    override val id: Long,
    override val nombre: String,
    override val apellido: String,
    override val esChofer: Boolean,
    val telefono: Int,
    val saldo: Double,
    val amigos: List<AmigoDTO>
): PerfilDTO

fun Viajero.toPerfilDTO() = PerfilViajeroDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    telefono = telefono,
    saldo = saldo,
    amigos = amigos.map { it.toAmigoDTO() }
)

data class PerfilChoferDTO(
    override val id: Long,
    override val nombre: String,
    override val apellido: String,
    override val esChofer: Boolean,
    val precioBase: Double,
    val dominio: String,
    val descripcion: String,
    val modelo: String,
    val anio: Int,
    val tipo: String,
): PerfilDTO

fun Conductor.toPerfilDTO() = PerfilChoferDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    precioBase = precioBaseDelViaje,
    dominio = vehiculo.patente,
    descripcion = vehiculo.marca,
    modelo = vehiculo.modelo,
    anio = vehiculo.anio,
    tipo = vehiculo.tipoVehiculo().javaClass.simpleName.toString(),
)


data class AmigoDTO(
    val nombreYApellido : String,
    val username: String,
    val id: Long
)

fun Usuario.toAmigoDTO() = AmigoDTO(
    nombreYApellido = "$nombre $apellido",
    username = username,
    id = id
)