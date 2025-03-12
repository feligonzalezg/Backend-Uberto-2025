package uberto.backendgrupo72025.Domain

import TipoVehiculo
import java.time.LocalDate

class Vehiculo(
    val marca: String,
    val modelo : String,
    val patente : String,
    val anio: Int,
    val tipoVehiculo : TipoVehiculo,
) {

    fun antiguedad(): Int = LocalDate.now().year - anio

    fun calculoPlusPorTipoVehiculo(viaje: Viaje): Double = tipoVehiculo.calculoPlus(viaje)

    fun validar() {
        validarMarca()
        validarModelo()
        validarPatente()
        validarAnio()
    }

    fun esValidoMarca() = marca.isNotEmpty()
    fun validarMarca() {
        if (!esValidoMarca()) throw RuntimeException("La marca del vehículo esta vacia")
    }

    fun esValidoModelo() = modelo.isNotEmpty()
    fun validarModelo() {
        if (!esValidoModelo()) throw RuntimeException("El modelo del vehículo esta vacio")
    }

    fun esValidoPatente() = patente.isNotEmpty()
    fun validarPatente() {
        if (!esValidoPatente()) throw RuntimeException("La patente del vehículo esta vacia")
    }

    fun esValidoAnio() = anio >= 1867
    fun validarAnio() {
        if (!esValidoAnio()) throw RuntimeException("El año debe ser realista, no puede ser de un año anterior al de su invención")
    }
}

