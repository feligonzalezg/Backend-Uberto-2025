package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDateTime

abstract class Usuario(
//    var id: Long?= 0,
    val nombre: String,
    val apellido: String,
    var edad: Int,
    val username: String,
    val contrasenia: String,
    val viajes: MutableList<Viaje> = mutableListOf(),
    var telefono: Int,
    val comentarios: MutableList<Comentario> = mutableListOf(),
    val esChofer : Boolean = false
): ItemRepo {
    override var id: Long = -1

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



    fun agregarComentario(comentario: Comentario) {
        if (!comentarioValido(comentario)) throw Exception("No se puede calificar")
        comentarios.add(comentario)
    }

    private fun comentarioValido(comentario: Comentario): Boolean = !comentario.autor.esChofer

    fun agregarViaje(viaje: Viaje) {
        viajes.add(viaje)
    }

    fun viajesRealizados() = viajes.filter { !it.estaPendiente() }

    fun viajesPendientes() = viajes.filter { it.estaPendiente() }

}
