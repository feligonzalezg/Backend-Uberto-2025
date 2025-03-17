package uberto.backendgrupo72025.Domain

import TipoVehiculo
import uberto.backendgrupo72025.Repository.ItemRepo
import java.time.LocalDate

class Vehiculo(
//    var id: Long? = 0,
    val marca: String,
    val modelo : String,
    val patente : String,
    val anio: Int,
    val tipoVehiculo : TipoVehiculo,
) : ItemRepo {
    override var id: Long = -1

    fun antiguedad(): Int = LocalDate.now().year - anio

    fun calculoPlusPorTipoVehiculo(cantidadDePasajeros: Int, duracion: Int): Double = tipoVehiculo.calculoPlus(cantidadDePasajeros, duracion)

    fun validar() {
        validarMarca()
        validarModelo()
        validarPatente()
        validarAnio()
        tipoVehiculo.validaLaCondicion(this)
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

