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
    val marca: String?,
    val modelo: String?,
    val anio: Int?,
    val foto:String
)

fun UsuarioDTO.toPerfilViajeroDTO() = PerfilViajeroDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    telefono = telefono!!,
    esChofer = esChofer,
    saldo = saldo!!,
    amigos = amigos!!,
    foto = foto
)

fun UsuarioDTO.toPerfilChoferDTO() = PerfilChoferDTO(
    id = id,
    nombre = nombre,
    apellido = apellido,
    esChofer = esChofer,
    precioBase = precioBase!!,
    dominio = dominio!!,
    marca = marca!!,
    modelo = modelo!!,
    anio = anio!!,
    foto = foto
)