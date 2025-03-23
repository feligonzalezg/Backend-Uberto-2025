package uberto.backendgrupo72025.Service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Domain.Viajero
import uberto.backendgrupo72025.Repository.ViajeRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ViajeService(
    val viajeRepository: ViajeRepository
) {

    fun getAllViajes() = viajeRepository.findAll()

    fun getViajeById(idViaje: Long) = viajeRepository.findById(idViaje)

    fun getViajesByViajeroId(idViajero: Long) = getAllViajes().filter { it.viajero.id == idViajero }

    fun getViajesByConductorId(idConductor: Long) = getAllViajes().filter { it.conductor.id == idConductor }

    fun getViajesPendientes(viajes: List<Viaje>) = viajes.filter { it.viajePendiente() }

    fun getViajesRealizados(viajes: List<Viaje>) = viajes.filter { it.viajeFinalizado() }

    fun crearViaje(viajeDTO: ViajeDTO, viajero: Viajero, conductor: Conductor): Viaje {
        val viaje = viajeDTO.toViaje(viajero, conductor)
        viajeRepository.save(viaje)
        return viaje
    }

    fun updateViaje(viaje: Viaje) {
        viajeRepository.update(viaje)
    }

    fun getViajesRealizadosByUsuario(idUsuario: Long, esChofer: Boolean): List<ViajeDTO> {
        return if (esChofer) {
            getViajesRealizados(getViajesByConductorId(idUsuario)).map { it.toViajeDTO(it.viajero.nombreYApellido()) }
        } else {
            getViajesRealizados(getViajesByViajeroId(idUsuario)).map { it.toViajeDTO(it.conductor.nombreYApellido()) }
        }
    }

    fun getViajesPendientesByUsuario(idUsuario: Long, esChofer: Boolean): List<ViajeDTO> {
        return if (esChofer) {
            getViajesPendientes(getViajesByConductorId(idUsuario)).map { it.toViajeDTO(it.viajero.nombreYApellido()) }
        } else {
            getViajesPendientes(getViajesByViajeroId(idUsuario)).map { it.toViajeDTO(it.conductor.nombreYApellido()) }
        }
    }

    fun getViajesConductorFiltrados(idConductor: Long, filtroDTO: FiltroDTO): List<ViajeDTO> {
        val viajesPendientes = getViajesPendientes(getViajesByConductorId(idConductor))
        return viajesPendientes.filter {
            (filtroDTO.usernameViajero.isBlank() || it.viajero.username.contains(filtroDTO.usernameViajero, ignoreCase = true)) &&
                    (filtroDTO.origen.isBlank() || it.origen.contains(filtroDTO.origen, ignoreCase = true)) &&
                    (filtroDTO.destino.isBlank() || it.destino.contains(filtroDTO.destino, ignoreCase = true)) &&
                    (filtroDTO.cantidadDePasajeros == 0 || it.cantidadDePasajeros == filtroDTO.cantidadDePasajeros)
        }.map { it.toViajeDTO(it.viajero.nombreYApellido()) }
    }

    fun getTotalFacturado(idConductor: Long): Double {
        return viajeRepository.findAll()
            .filter { it.viajeFinalizado() && it.conductor.id == idConductor }
            .sumOf { it.importe }
    }
}