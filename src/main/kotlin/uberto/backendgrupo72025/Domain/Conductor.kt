package uberto.backendgrupo72025.Domain

class Conductor(
    nombreYApellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajes: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    esChofer: Boolean,
    val vehiculo: Vehiculo,
    val precioBaseDelViaje: Int
) : Usuario(nombreYApellido, edad, username, contrasenia, viajes, telefono, comentarios, esChofer) {


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