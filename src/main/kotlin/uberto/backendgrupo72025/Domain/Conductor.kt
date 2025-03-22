package uberto.backendgrupo72025.Domain

import java.time.LocalDateTime

class Conductor(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajes: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    esChofer: Boolean,
    var vehiculo: Vehiculo,
    var precioBaseDelViaje: Double
) : Usuario(nombre, apellido, edad, username, contrasenia, viajes, telefono, comentarios, esChofer) {


    override fun validacionesPorUsuario() {
        validarVehiculo()
        validarPrecioBaseDelViaje()
    }

    private fun validarVehiculo() {
        vehiculo.validar()
    }

    private fun esValidoPrecioBaseDelViaje() = precioBaseDelViaje > 0
    private fun validarPrecioBaseDelViaje() {
        if (!esValidoPrecioBaseDelViaje()) throw RuntimeException("El precio base no puede ser menor o igual a 0")
    }

    fun cantidadComentarios() = comentarios.size

    fun puntajeTotal() = comentarios.sumOf { it.estrellas }

    fun calificacion() = if (cantidadComentarios()>0) puntajeTotal().toDouble() / cantidadComentarios().toDouble() else 0.0

    fun disponible(fechaNueva: LocalDateTime, duracion: Int) = !viajes.any { it.seSolapan(fechaNueva, duracion) }

    fun importeViaje(cantidadDePasajeros: Int, duracion: Int) =
        precioBaseDelViaje + duracion * vehiculo.calculoPlusPorTipoVehiculo(cantidadDePasajeros, duracion)


}