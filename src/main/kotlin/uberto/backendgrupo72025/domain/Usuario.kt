package uberto.backendgrupo72025.domain

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*
import uberto.backendgrupo72025.dto.UsuarioLoginDTO

@MappedSuperclass
abstract class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long= 0,
    @Column
    var nombre: String="",
    @Column
    var apellido: String="",
    @Column
    var edad: Int= 0,
    @Column(unique = true)
    val username: String="",
    @Column
    val contrasenia: String="",
    @Column
    var telefono: Int= 0,
    @Column
    val esChofer : Boolean = false,
    @Column
    var foto : String = "1"
) {

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
