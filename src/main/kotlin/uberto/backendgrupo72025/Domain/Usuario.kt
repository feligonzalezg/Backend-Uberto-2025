import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.Domain.Comentario
import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Domain.Viaje

abstract class Usuario(
    val nombreYApellido: String,
    var edad : Int,
    val username: String,
    val contrasenia: String,
    val viajesRealizados: MutableList<Viaje> = mutableListOf(),
    var telefono : Int,
    val comentarios: MutableList<Comentario> = mutableListOf()
) {

    //access
    fun accesoUsuario(user: UsuarioLoginDTO): Boolean {
        return user.usuario == username && user.contrasenia == contrasenia
    }

    // Validaciones
    fun esValidoNombre() = nombreYApellido.isNotEmpty()
    fun validarNombre() {
        if(!esValidoNombre()) throw RuntimeException("El nombre esta vacio")
    }

    fun esValidoUsername() = nombreYApellido.isNotEmpty()
    fun validarUsername() {
        if(!esValidoUsername()) throw RuntimeException("El username esta vacio")
    }

    fun esValidoContrasenia() = nombreYApellido.isNotEmpty()
    fun validarContrasenia() {
        if(!esValidoContrasenia()) throw RuntimeException("La contraseña esta vacio")
    }

    fun esValidaLaEdad() = edad > 0
    fun validarEdad() {
        if(!esValidaLaEdad() ) throw RuntimeException("La edad ingresada no puede ser menor o igual a 0")
    }

    fun esValidoElTelefono() = telefono > 0
    fun validarTelefono() {
        if(!esValidoElTelefono() ) throw RuntimeException("El telefono ingresado no puede ser menor o igual a 0")
    }

    abstract fun validacionesPorUsuario()

    fun validar() {
        validarNombre()
        validarContrasenia()
        validarUsername()
        validarEdad()
        validarTelefono()
        validacionesPorUsuario()
    }

    fun getComentarios(): List<Comentario> = comentarios.toList()

    fun agregarComentario(comentario: Comentario) {
        if (!comentarioValido(comentario)) throw Exception("No se puede calificar")
        comentarios.add(comentario)
    }

    private fun comentarioValido(comentario: Comentario): Boolean = comentario.autor is Viajero
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
        validarVehiculo()
        validarPrecioBaseDelViaje()
    }

    private fun validarVehiculo() { vehiculo.validar() }

    private fun esValidoPrecioBaseDelViaje() = precioBaseDelViaje > 0
    private fun validarPrecioBaseDelViaje() {
        if (!esValidoPrecioBaseDelViaje()) throw RuntimeException("El precio base no puede ser menor o igual a 0")
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
        validarSaldo()
    }

    fun agregarSaldo(saldoAAgregar: Double) {
        saldo += saldoAAgregar
    }

    private fun esSaldoValido() = saldo >= 0.0
    private fun validarSaldo() {
        if (!esSaldoValido()) throw RuntimeException("El saldo no puede ser menor a 0")
    }

}