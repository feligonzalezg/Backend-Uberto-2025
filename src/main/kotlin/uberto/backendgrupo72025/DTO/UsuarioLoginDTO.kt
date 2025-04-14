package uberto.backendgrupo72025.DTO


import uberto.backendgrupo72025.Domain.Usuario

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

fun Usuario.toLoginDTO() = LoginDTO(
    id =  id,
    esChofer = esChofer
)


