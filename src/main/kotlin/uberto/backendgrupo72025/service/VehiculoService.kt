package uberto.backendgrupo72025.service


import org.springframework.stereotype.Service
import uberto.backendgrupo72025.dto.PerfilChoferDTO
import uberto.backendgrupo72025.domain.*
import uberto.backendgrupo72025.repository.VehiculoRepository

@Service
class VehiculoService(
    val vehiculoRepository: VehiculoRepository,
) {

    fun getAll() = vehiculoRepository.findAll()

    fun createVehiculo(dominio: String, marca: String, modelo: String, anio: Int): Vehiculo {
        val nuevoVehiculo = Vehiculo(marca, modelo, dominio, anio)
        nuevoVehiculo.validar()
        vehiculoRepository.save(nuevoVehiculo)
        return nuevoVehiculo
    }

    fun actualizarVehiculo(conductor: Conductor, choferDTO: PerfilChoferDTO): Vehiculo {
        return createVehiculo(choferDTO.dominio, choferDTO.marca, choferDTO.modelo, choferDTO.anio)
    }

    fun validarCambioVehiculo(conductor: Conductor, choferDTO: PerfilChoferDTO): Boolean =
        conductor.vehiculo.dominio != choferDTO.dominio ||
        conductor.vehiculo.marca != choferDTO.marca ||
        conductor.vehiculo.modelo != choferDTO.modelo ||
        conductor.vehiculo.anio != choferDTO.anio
}