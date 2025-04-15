package uberto.backendgrupo72025.domain

class Simple(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    telefono: Int,
    esChofer: Boolean,
    foto: String,
    vehiculo: Vehiculo,
    precioBaseDelViaje: Double
) : Conductor(nombre, apellido, edad, username, contrasenia, telefono, esChofer, foto, vehiculo, precioBaseDelViaje) {

    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = 1000.toDouble()
}

class Ejecutivo(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    telefono: Int,
    esChofer: Boolean,
    foto: String,
    vehiculo: Vehiculo,
    precioBaseDelViaje: Double
) : Conductor(nombre, apellido, edad, username, contrasenia, telefono, esChofer, foto, vehiculo, precioBaseDelViaje) {

    override var mensaje: String = "No cumple con los requisitos de la antiguedad, ya que debe ser de menos de 10 años"

    override fun esValidaLaCondicion(vehiculo: Vehiculo) = vehiculo.antiguedad() <= 10

    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = costoPorCantidadDePasajeros(cantidadDePasajeros)

    private fun costoPorCantidadDePasajeros(cantidadDePasajeros: Int):Double = if (cantidadDePasajeros == 1) 2000.toDouble() else 1500.toDouble()
}

class Moto(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    telefono: Int,
    esChofer: Boolean,
    foto: String,
    vehiculo: Vehiculo,
    precioBaseDelViaje: Double
) : Conductor(nombre, apellido, edad, username, contrasenia, telefono, esChofer, foto, vehiculo, precioBaseDelViaje) {

    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = costoPorDuracion(duracion)

    private fun costoPorDuracion(duracion: Int): Double = if (duracion <= 30) 500.toDouble() else 600.toDouble()
}