package uberto.backendgrupo72025.DTO


data class UsuarioDTO(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val esChofer: Boolean,
    val telefono: Int?,
    val saldo: Double?,
    val amigos: List<AmigoDTO>?,
    val precioBase: Double?,
    val dominio: String?,
    val descripcion: String?,
    val modelo: String?,
    val anio: Int?,
    val tipo: String?,
)

fun UsuarioDTO.toPerfilViajeroDTO() = PerfilViajeroDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    telefono = telefono!!,
    esChofer = esChofer,
    saldo = saldo!!,
    amigos = amigos!!
)

fun UsuarioDTO.toPerfilChoferDTO() = PerfilChoferDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    precioBase = precioBase!!,
    dominio = dominio!!,
    descripcion = descripcion!!,
    modelo = modelo!!,
    anio = anio!!,
    tipo = tipo!!,
)