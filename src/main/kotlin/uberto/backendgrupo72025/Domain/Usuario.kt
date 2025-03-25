package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDateTime

abstract class Usuario(
//    var id: Long?= 0,
    var nombre: String,
    var apellido: String,
    var edad: Int,
    val username: String,
    val contrasenia: String,
    var telefono: Int,
    val esChofer : Boolean = false,
    val foto : String = "1"
): ItemRepo {
    override var id: Long = -1

    fun nombreYApellido() = "$nombre $apellido"

    //access
    fun accesoUsuario(user: UsuarioLoginDTO): Boolean {
        return user.usuario == username && user.contrasenia == contrasenia
    }

    // Validaciones
    fun esValidoNombre() = nombre.isNotEmpty()
    fun validarNombre() {
        if (!esValidoNombre()) throw RuntimeException("El nombre esta vacio")
    }

    fun esValidoApellido() = apellido.isNotEmpty()
    fun validarApellido() {
        if (!esValidoApellido()) throw RuntimeException("El apellido esta vacio")
    }

    fun esValidoUsername() = username.isNotEmpty()
    fun validarUsername() {
        if (!esValidoUsername()) throw RuntimeException("El username esta vacio")
    }

    fun esValidoContrasenia() = contrasenia.isNotEmpty()
    fun validarContrasenia() {
        if (!esValidoContrasenia()) throw RuntimeException("La contraseña esta vacio")
    }

    fun esValidaLaEdad() = edad > 0
    fun validarEdad() {
        if (!esValidaLaEdad()) throw RuntimeException("La edad ingresada no puede ser menor o igual a 0")
    }

    fun esValidoElTelefono() = telefono > 0
    fun validarTelefono() {
        if (!esValidoElTelefono()) throw RuntimeException("El telefono ingresado no puede ser menor o igual a 0")
    }

    abstract fun validacionesPorUsuario()

    fun validar() {
        validarNombre()
        validarApellido()
        validarContrasenia()
        validarUsername()
        validarEdad()
        validarTelefono()
        validacionesPorUsuario()
    }
}
