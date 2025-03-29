package uberto.backendgrupo72025.domain

abstract class Conductor(
//  id: Long = 0
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    telefono: Int,
    esChofer: Boolean,
    foto : String,
    var vehiculo: Vehiculo = Vehiculo(),
    var precioBaseDelViaje: Double = 0.0
) : Usuario(nombre, apellido, edad, username, contrasenia, telefono, esChofer,foto) {

    open var mensaje = ""

    override fun validacionesPorUsuario() {
        validarVehiculo()
        validarPrecioBaseDelViaje()
        validarCondicion(vehiculo)
    }

    private fun validarVehiculo() {
        vehiculo.validar()
    }

    private fun esValidoPrecioBaseDelViaje() = precioBaseDelViaje > 0
    private fun validarPrecioBaseDelViaje() {
        if (!esValidoPrecioBaseDelViaje()) throw BadRequestException("El precio base no puede ser menor o igual a 0")
    }

    fun importeViaje(cantidadDePasajeros: Int, duracion: Int) =
        precioBaseDelViaje + duracion * calculoPlusPorTipoVehiculo(cantidadDePasajeros, duracion)

    fun calculoPlusPorTipoVehiculo(cantidadDePasajeros: Int, duracion: Int): Double = calculoPlus(cantidadDePasajeros, duracion)

    abstract fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double

    fun validarCondicion(vehiculo: Vehiculo) {
        if(!esValidaLaCondicion(vehiculo)) throw BadRequestException(mensaje)
    }

    open fun esValidaLaCondicion(vehiculo: Vehiculo): Boolean = true
}