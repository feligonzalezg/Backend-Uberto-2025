package uberto.backendgrupo72025.domain

import jakarta.persistence.*

@Entity
@DiscriminatorValue(value = "C")
abstract class Conductor(
    id: Long = 0,
    nombre: String="",
    apellido: String="",
    edad: Int=0,
    username: String="",
    contrasenia: String="",
    telefono: Int=0,
    esChofer: Boolean=true,
    foto : String="",
    @OneToOne
    var vehiculo: Vehiculo = Vehiculo(),
    @Column
    var precioBaseDelViaje: Double = 0.0
) : Usuario(id,nombre, apellido, edad, username, contrasenia, telefono, esChofer,foto) {

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