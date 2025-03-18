package uberto.backendgrupo72025.Service

import uberto.backendgrupo72025.Domain.Viajero
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.Repository.*


@Service
class UsuarioService(
    val viajeroRepository: ViajeroRepository,
    val conductorRepository: ConductorRepository,
    val viajeService: ViajeService,
) {

    fun getConductores() = conductorRepository.findAll()

    fun getViajeros() = viajeroRepository.findAll()

    fun getUsuarios() = getConductores() + getViajeros()


    fun getUsuarioLogin(user: UsuarioLoginDTO): LoginDTO {
        val usuario = getUsuarios().filter { it.accesoUsuario(user) }
        if (usuario.isNotEmpty()) {
            return usuario.first().toDTO1()
        } else {
            throw RuntimeException("Los datos ingresados son incorrectos")
        }
    }

    // viajero
    fun getViajeroById(id: Long) = viajeroRepository.findById(id)

    fun getAmigos(id: Long) = getViajeroById(id).amigos


    //chofer
    fun getConductorById(id: Long) = conductorRepository.findById(id)

    fun getComentarios(id: Long, esChofer: Boolean): List<ComentarioDTO> {
        return if (esChofer) {
            getConductorById(id).comentarios.map { it.toComentarioDTO() }
        } else {
            getViajeroById(id).comentarios.map { it.toComentarioDTO() }
        }
    }

    fun getCalificacionChofer(id: Long) = getConductorById(id).calificacion()


    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO) =
        getConductores().filter { it.disponible(busquedaDTO.fecha, busquedaDTO.duracion) }
            .map { it.toConductorDTO(busquedaDTO.cantidadDePasajeros, busquedaDTO.duracion) }


    @Transactional
    fun contratarViaje(viajeDTO: ViajeDTO) {
        val viajero = getViajeroById(viajeDTO.idViajero)
        val conductor = getConductorById(viajeDTO.idConductor)
        validarPuedeRealizarseViaje(viajero, viajeDTO.importe)
        val viaje = viajeService.crearViaje(viajeDTO)
        viajero.contratarViaje(viaje)
        conductor.agregarViaje(viaje)
        viajeroRepository.save(viajero)
        conductorRepository.save(conductor)
    }

    fun validarPuedeRealizarseViaje(viajero: Viajero, costoDelViaje: Double) {
//        conductor.disponible(viaje.fecha, viaje.duracion)
        viajero.validarSaldoSuficiente(costoDelViaje)
    }

    fun getUsuarioPerfil(id: Long, esChofer: Boolean): PerfilDTO {
        return if (esChofer) {
            getConductorById(id).toPerfilDTO()
        } else {
            getViajeroById(id).toPerfilDTO()
        }
    }

    fun getViajesRealizadosByUsuario(id: Long, esChofer: Boolean): List<ViajeCardDTO> {
        return if (esChofer) {
            val conductor = getConductorById(id)
            conductor.viajesRealizados().map { it.toViajeCardDTO(conductor) }
        } else {
            getViajeroById(id).viajesRealizados().map { it.toViajeCardDTO(getConductorById(it.idConductor)) }
        }
    }

    fun getViajesPendientesByUsuario(id: Long, esChofer: Boolean): List<ViajeCardDTO> {
        if (esChofer) throw Exception("Inaccesible")
        return getViajeroById(id).viajesPendientes().map { it.toViajeCardDTO(getConductorById(it.idConductor)) }
    }


}