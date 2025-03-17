import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Domain.Viaje

interface TipoVehiculo {
    var mensaje :String

    fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double

    fun esValidaLaCondicion(vehiculo: Vehiculo) = true

    fun validaLaCondicion(vehiculo: Vehiculo) {
        if(esValidaLaCondicion(vehiculo)) throw RuntimeException(mensaje)
    }
}

object Simple: TipoVehiculo {
    override var mensaje: String = ""
    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = 1000.toDouble()
}

object Ejecutivo: TipoVehiculo {
    override var mensaje: String = "No cumple con los requisitos de la antiguedad, el vehiculo debe tener menor a 10 años"
    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = costoPorCantidadDePasajeros(cantidadDePasajeros)

    override fun esValidaLaCondicion(vehiculo: Vehiculo) = vehiculo.antiguedad() < 10

    private fun costoPorCantidadDePasajeros(cantidadDePasajeros: Int):Double = if (cantidadDePasajeros == 1) 2000.toDouble() else 1500.toDouble()
}

object Moto: TipoVehiculo {
    override var mensaje: String = ""

    override fun calculoPlus(cantidadDePasajeros: Int, duracion: Int): Double = costoPorDuracion(duracion)

    private fun costoPorDuracion(duracion: Int): Double = if (duracion <= 30) 500.toDouble() else 600.toDouble()
}