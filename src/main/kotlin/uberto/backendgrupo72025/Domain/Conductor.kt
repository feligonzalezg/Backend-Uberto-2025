package uberto.backendgrupo72025.Domain

import java.time.LocalDateTime

class Conductor(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    telefono: Int,
    esChofer: Boolean,
    var vehiculo: Vehiculo,
    var precioBaseDelViaje: Double
) : Usuario(nombre, apellido, edad, username, contrasenia, telefono, esChofer) {

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

    fun importeViaje(cantidadDePasajeros: Int, duracion: Int) =
        precioBaseDelViaje + duracion * vehiculo.calculoPlusPorTipoVehiculo(cantidadDePasajeros, duracion)

}