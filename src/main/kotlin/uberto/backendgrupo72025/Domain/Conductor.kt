package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Domain.Usuario

class Conductor(
    nombreYApellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajesRealizados: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    val vehiculo: Vehiculo,
    val precioBaseDelViaje: Int
) : Usuario(nombreYApellido, edad, username, contrasenia, viajesRealizados, telefono, comentarios) {

    override val esConductor: Boolean = true

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

}