package uberto.backendgrupo72025.Domain

import java.time.LocalDate
import java.time.LocalDateTime

class Vehiculo(
    val marca: String,
    val modelo : String,
    val patente : String,
    val año: Int,
    val tipoVehiculo : TipoVehiculo,
) {

    fun antiguedad(): Int = LocalDate.now().year - año

}

interface TipoVehiculo {
    fun calculoExtra(viaje: Viaje): Double
}

object Simple: TipoVehiculo {
    override fun calculoExtra(viaje: Viaje): Double = 1000.toDouble()
}

object Ejecutivo: TipoVehiculo {
    override fun calculoExtra(viaje: Viaje): Double = costoPorCantidadDePasajeros(viaje)

    private fun costoPorCantidadDePasajeros(viaje: Viaje):Double =
        if (viaje.cantidadDePasajeros == 1) 2000.toDouble() else 1500.toDouble()
}

object Moto: TipoVehiculo {
    override fun calculoExtra(viaje: Viaje): Double = costoPorDuracion(viaje)

    private fun costoPorDuracion(viaje: Viaje): Double =
        if (viaje.duracion <= 30) 500.toDouble() else 600.toDouble()
}