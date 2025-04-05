package uberto.backendgrupo72025.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "vehiculos")
class Vehiculo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var marca: String = "",
    @Column
    var modelo : String = "",
    @Column(unique = true)
    var dominio: String = "",
    @Column
    var anio: Int = 0,
    @Column
    var active: Boolean = true
) {

    fun antiguedad(): Int = LocalDate.now().year - anio

    fun validar() {
        validarMarca()
        validarModelo()
        validarDominio()
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

    fun esValidoDominio() = dominio.isNotEmpty()
    fun validarDominio() {
        if (!esValidoDominio()) throw RuntimeException("El dominio del vehículo esta vacia")
    }

    fun esValidoAnio() = anio >= 1867
    fun validarAnio() {
        if (!esValidoAnio()) throw RuntimeException("El año debe ser realista, no puede ser de un año anterior al de su invención")
    }
}

