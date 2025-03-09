import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.domain.Vehiculo
import uberto.backendgrupo72025.domain.Viaje

abstract class Usuario(
    val nombreYApellido: String,
    var edad : Int,
    val username: String,
    val contrasenia: String,
    val viajesRealizados: MutableList<Viaje> = mutableListOf(),
    var telefono : Int
) {


    //access
    fun accesoUsuario(user: UsuarioLoginDTO): Boolean {
        return user.usuario == username && user.contrasenia == contrasenia
    }


    // Validaciones
    fun esValidoNombre() = nombreYApellido.isEmpty()
    fun validarNombre() {
        if(esValidoNombre()) throw RuntimeException("El nombre esta vacio")
    }

    fun esValidoUsername() = nombreYApellido.isEmpty()
    fun validarUsername() {
        if(esValidoUsername()) throw RuntimeException("El username esta vacio")
    }

    fun esValidoContrasenia() = nombreYApellido.isEmpty()
    fun validarContrasenia() {
        if(esValidoContrasenia()) throw RuntimeException("El contraseña esta vacio")
    }

    fun validar() {
        validarNombre()
        validarContrasenia()
        validarUsername()
    }
}

class Conductor(
    nombreYApellido: String,
    username: String,
    edad :Int,
    contrasenia: String,
    viajesRealizados: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    val vehiculo: Vehiculo,
    val precioBaseDelViaje : Int
) : Usuario(nombreYApellido,edad, username, contrasenia, viajesRealizados,telefono) {


}

class Viajero(
    nombreYApellido: String,
    username: String,
    contrasenia: String,
    edad :Int,
    viajesRealizados: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    var saldo : Double,
) : Usuario(nombreYApellido,edad,  username, contrasenia, viajesRealizados,telefono) {


}