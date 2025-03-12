import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Domain.Viaje

interface TipoVehiculo {
    fun calculoPlus(viaje: Viaje): Double

    fun validarCondicion(vehiculo: Vehiculo) = true
}

object Simple: TipoVehiculo {
    override fun calculoPlus(viaje: Viaje): Double = 1000.toDouble()
}

object Ejecutivo: TipoVehiculo {
    override fun calculoPlus(viaje: Viaje): Double = costoPorCantidadDePasajeros(viaje.cantidadDePasajeros)

    override fun validarCondicion(vehiculo: Vehiculo) = vehiculo.antiguedad() < 10

    private fun costoPorCantidadDePasajeros(cantidadDePasajeros: Int):Double = if (cantidadDePasajeros == 1) 2000.toDouble() else 1500.toDouble()
}

object Moto: TipoVehiculo {
    override fun calculoPlus(viaje: Viaje): Double = costoPorDuracion(viaje.duracion)

    private fun costoPorDuracion(duracion: Int): Double = if (duracion <= 30) 500.toDouble() else 600.toDouble()
}