package uberto.backendgrupo72025.dto


import uberto.backendgrupo72025.domain.Usuario

data class UsuarioLoginDTO(
    val usuario : String,
    val contrasenia : String,
)

fun Usuario.toDTO() = UsuarioLoginDTO(
    usuario = username,
    contrasenia =  contrasenia,

)

data class LoginDTO(
    val id : Long,
    val esChofer: Boolean
)

fun Usuario.toDTO1() = LoginDTO(
    id =  id,
    esChofer = esChofer
)


