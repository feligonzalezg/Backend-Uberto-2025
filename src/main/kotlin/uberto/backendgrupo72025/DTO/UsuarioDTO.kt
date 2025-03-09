package uberto.backendgrupo72025.DTO

import Usuario

data class UsuarioLoginDTO(
    val usuario : String,
    val contrasenia : String)

fun Usuario.toDTO() = UsuarioLoginDTO(
    usuario = username,
    contrasenia =  contrasenia
)