package uberto.backendgrupo72025.Service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Domain.Viajero
import uberto.backendgrupo72025.Repository.ViajeRepository

@Service
class ViajeService(
    val viajeRepository: ViajeRepository,
    val comentarioService: ComentarioService
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

    fun getViajesRealizadosByUsuario(idUsuario: Long, esChofer: Boolean): ViajesCompletadosDTO {
        lateinit var viajesRealizados: List<ViajeDTO>
        var totalFacturado = 0.0
         if (esChofer) {
             viajesRealizados = getViajesRealizados(getViajesByConductorId(idUsuario)).map { it.toViajeDTO(it.viajero.nombreYApellido(),it.viajero.foto, viajeCalificable(it)) }
             totalFacturado = viajesRealizados.sumOf { it.importe }
        } else {
             viajesRealizados = getViajesRealizados(getViajesByViajeroId(idUsuario)).map { it.toViajeDTO(it.conductor.nombreYApellido(), it.conductor.foto, viajeCalificable(it)) }
        }
        return ViajesCompletadosDTO(viajesRealizados, totalFacturado)
    }

    fun viajeCalificable(viaje: Viaje) = !viaje.viajePendiente() && !comentarioService.viajeCalificado(viaje.id)

    fun getViajesPendientesByUsuario(idUsuario: Long, esChofer: Boolean): List<ViajeDTO> {
        return if (esChofer) {
            getViajesPendientes(getViajesByConductorId(idUsuario)).map { it.toViajeDTO(it.viajero.nombreYApellido(), it.viajero.foto, viajeCalificable(it)) }
        } else {
            getViajesPendientes(getViajesByViajeroId(idUsuario)).map { it.toViajeDTO(it.conductor.nombreYApellido(),it.conductor.foto, viajeCalificable(it)) }
        }
    }

    fun getViajesConductorFiltrados(idConductor: Long, filtroDTO: FiltroDTO): List<ViajeDTO> {
        val viajesPendientes = getViajesPendientes(getViajesByConductorId(idConductor))
        return viajesPendientes.filter {
            (filtroDTO.usernameViajero.isBlank() || it.viajero.username.contains(filtroDTO.usernameViajero, ignoreCase = true)) &&
                    (filtroDTO.origen.isBlank() || it.origen.contains(filtroDTO.origen, ignoreCase = true)) &&
                    (filtroDTO.destino.isBlank() || it.destino.contains(filtroDTO.destino, ignoreCase = true)) &&
                    (filtroDTO.cantidadDePasajeros == 0 || it.cantidadDePasajeros == filtroDTO.cantidadDePasajeros)
        }.map { it.toViajeDTO(it.viajero.nombreYApellido(), it.viajero.foto, viajeCalificable(it)) }
    }

    @Transactional
    fun calificarViaje(idUsuario: Long, calificacion: CalificacionDTO): ComentarioDTO {
        val viaje = getViajeById(calificacion.idViaje)
        val comentario = comentarioService.calificar(calificacion, viaje, idUsuario)
        return comentario.toComentarioDTO(viaje.conductor.nombreYApellido(), viaje.conductor.foto)
    }
}