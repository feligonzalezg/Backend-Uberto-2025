package uberto.backendgrupo72025.Service


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.PerfilChoferDTO
import uberto.backendgrupo72025.Domain.*
import uberto.backendgrupo72025.Repository.VehiculoRepository

@Service
class VehiculoService(
    val vehiculoRepository: VehiculoRepository,
) {

    fun getAll() = vehiculoRepository.findAll()

    fun createVehiculo(dominio: String, marca: String, modelo: String, anio: Int, tipoVehiculo: String): Vehiculo {
        val nuevoTipoVehiculo = obtenerTipoVehiculo(tipoVehiculo)
        val nuevoVehiculo = Vehiculo(marca, modelo, dominio, anio, nuevoTipoVehiculo)
        nuevoVehiculo.validar()
        vehiculoRepository.save(nuevoVehiculo)
        return nuevoVehiculo
    }

    fun obtenerTipoVehiculo(nombre: String): TipoVehiculo {
        return when (nombre.trim().lowercase()) {
            "auto" -> Simple
            "auto ejecutivo" -> Ejecutivo
            "moto" -> Moto
            else -> throw BadRequestException("Tipo de vehículo inválido: $nombre")
        }
    }

    fun actualizarVehiculo(conductor: Conductor, choferDTO: PerfilChoferDTO): Vehiculo {
        return createVehiculo(choferDTO.dominio, choferDTO.descripcion, choferDTO.modelo, choferDTO.anio, choferDTO.tipo)
    }

    fun validarCambioVehiculo(conductor: Conductor, choferDTO: PerfilChoferDTO): Boolean =
        conductor.vehiculo.patente != choferDTO.dominio || conductor.vehiculo.marca != choferDTO.descripcion ||
        conductor.vehiculo.modelo != choferDTO.modelo || conductor.vehiculo.tipoVehiculo.javaClass.simpleName.toString() != choferDTO.tipo
}