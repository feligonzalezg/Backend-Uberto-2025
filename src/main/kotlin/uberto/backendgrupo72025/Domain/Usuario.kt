import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Domain.Viaje

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
        if(esValidoContrasenia()) throw RuntimeException("La contraseña esta vacio")
    }

    fun esValidaLaEdad() = edad > 0
    fun validarEdad() {
        if(esValidaLaEdad() ) throw RuntimeException("La edad ingresada no puede ser menor o igual a 0")
    }

    fun esValidoElTelefono() = telefono > 0
    fun validarTelefono() {
        if(esValidoElTelefono() ) throw RuntimeException("El telefono ingresado no puede ser menor o igual a 0")
    }

    abstract fun validacionesPorUsuario()

    fun validar() {
        validarNombre()
        validarContrasenia()
        validarUsername()
        validarEdad()
        validarTelefono()
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

    override fun validacionesPorUsuario() {
        TODO("Not yet implemented")
    }


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

    override fun validacionesPorUsuario() {
        TODO("Not yet implemented")
    }

}