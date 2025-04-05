package uberto.backendgrupo72025.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.dto.*
import uberto.backendgrupo72025.domain.Conductor
import uberto.backendgrupo72025.domain.Viaje
import uberto.backendgrupo72025.domain.Viajero
import uberto.backendgrupo72025.repository.ViajeRepository

@Service
class ViajeService(
    val viajeRepository: ViajeRepository,
    val comentarioService: ComentarioService
) {

    fun getAllViajes() = viajeRepository.findAll()

    fun getViajeById(idViaje: Long) = viajeRepository.findById(idViaje).get()

    fun getViajesByUsuarioId(idUsuario: Long) = viajeRepository.findByViajeroIdOrConductorId(idUsuario)

    fun crearViaje(viajeDTO: ViajeDTO, viajero: Viajero, conductor: Conductor): Viaje {
        val viaje = viajeDTO.toViaje(viajero, conductor)
        viajeRepository.save(viaje)
        return viaje
    }

    fun getViajesRealizadosByUsuario(idUsuario: Long, esChofer: Boolean): ViajesCompletadosDTO {
        lateinit var viajesRealizadosDTO: List<ViajeDTO>
        lateinit var viajesRealizados: List<Viaje>
        var totalFacturado = 0.0
         if (esChofer) {
             viajesRealizados = viajeRepository.findByConductorIdAndFechaFinBefore(idUsuario)
             viajesRealizadosDTO = viajesRealizados.map { it.toViajeDTO(it.viajero.nombreYApellido(),it.viajero.foto, viajeCalificable(it)) }
             totalFacturado = getTotalFacturado(idUsuario)
        } else {
             viajesRealizados = viajeRepository.findByViajeroIdAndFechaFinBefore(idUsuario)
             viajesRealizadosDTO = viajesRealizados.map { it.toViajeDTO(it.conductor.nombreYApellido(), it.conductor.foto, viajeCalificable(it)) }
        }
        return ViajesCompletadosDTO(viajesRealizadosDTO, totalFacturado)
    }

    fun getTotalFacturado(idUsuario: Long) = viajeRepository.sumTotalFacturadoByChoferId(idUsuario)

    fun viajeCalificable(viaje: Viaje) = !viaje.viajePendiente() && !comentarioService.viajeCalificado(viaje.id)

    fun getViajesPendientesByUsuario(idUsuario: Long, esChofer: Boolean): List<ViajeDTO> {
        lateinit var viajesPendientes: List<Viaje>
        if (esChofer) {
            viajesPendientes = viajeRepository.findByConductorIdAndFechaFinAfter(idUsuario)
            return viajesPendientes.map { it.toViajeDTO(it.viajero.nombreYApellido(), it.viajero.foto, viajeCalificable(it)) }
        } else {
            viajesPendientes = viajeRepository.findByViajeroIdAndFechaFinAfter(idUsuario)
            return viajesPendientes.map { it.toViajeDTO(it.conductor.nombreYApellido(),it.conductor.foto, viajeCalificable(it)) }
        }
    }

    fun getViajesConductorFiltrados(idConductor: Long, filtroDTO: FiltroDTO): List<ViajeDTO> {
      return viajeRepository.findViajesFiltradosByConductorId(idConductor,
          filtroDTO.usernameViajero, filtroDTO.origen, filtroDTO.destino, filtroDTO.cantidadDePasajeros)
            .map { it.toViajeDTO(it.viajero.nombreYApellido(), it.viajero.foto, viajeCalificable(it)) }
    }

    @Transactional
    fun calificarViaje(idUsuario: Long, calificacion: CalificacionDTO): ComentarioDTO {
        val viaje = getViajeById(calificacion.idViaje)
        val comentario = comentarioService.calificar(calificacion, viaje, idUsuario)
        return comentario.toComentarioDTO(viaje.conductor.nombreYApellido(), viaje.conductor.foto)
    }
}