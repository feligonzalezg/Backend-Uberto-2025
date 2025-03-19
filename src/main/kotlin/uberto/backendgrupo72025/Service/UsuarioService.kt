package uberto.backendgrupo72025.Service

import uberto.backendgrupo72025.Domain.Viajero
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.Domain.BadRequestException
import uberto.backendgrupo72025.Domain.UnauthorizedException
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Repository.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class UsuarioService(
    val viajeroRepository: ViajeroRepository,
    val conductorRepository: ConductorRepository,
    val viajeService: ViajeService,
    val comentarioService: ComentarioService
) {

    fun getConductores() = conductorRepository.findAll()

    fun getViajeros() = viajeroRepository.findAll()

    fun getUsuarios() = getConductores() + getViajeros()


    fun getUsuarioLogin(user: UsuarioLoginDTO): LoginDTO {
        val usuario = getUsuarios().filter { it.accesoUsuario(user) }
        if (usuario.isNotEmpty()) {
            return usuario.first().toDTO1()
        } else {
            throw UnauthorizedException("Los datos ingresados son incorrectos")
        }
    }

    // viajero
    fun getViajeroById(id: Long) = viajeroRepository.findById(id)

    fun getAmigos(id: Long) = getViajeroById(id).amigos


    //chofer
    fun getConductorById(id: Long) = conductorRepository.findById(id)

    fun getComentarios(id: Long, esChofer: Boolean): List<ComentarioDTO> {
        return if (esChofer) {
            getConductorById(id).comentarios.map { it.toComentarioDTO(getNombreViajero(it.viaje.idViajero)) }
        } else {
            getViajeroById(id).comentarios.map { it.toComentarioDTO(getNombreConductor(it.viaje.idConductor)) }
        }
    }

    fun getCalificacionChofer(id: Long) = getConductorById(id).calificacion()


    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO) =
        getConductores().filter { it.disponible(LocalDateTime.parse(busquedaDTO.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), busquedaDTO.duracion) }
            .map { it.toConductorDTO(busquedaDTO.cantidadDePasajeros, busquedaDTO.duracion) }

    @Transactional
    fun contratarViaje(viajeDTO: ViajeDTO) {
        val viajero = getViajeroById(viajeDTO.idViajero)
        val conductor = getConductorById(viajeDTO.idConductor)
        validarPuedeRealizarseViaje(viajero, viajeDTO.importe)
        val viaje = viajeService.crearViaje(viajeDTO)
        viajero.contratarViaje(viaje)
        conductor.agregarViaje(viaje)
        viajeroRepository.update(viajero)
        conductorRepository.update(conductor)
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

    fun getViajesRealizadosByUsuario(id: Long, esChofer: Boolean): List<ViajeDTO> {
        return if (esChofer) {
            val conductor = getConductorById(id)
            conductor.viajesRealizados().map { it.toViajeDTO(getNombreViajero(it.idViajero)) }
        } else {
            getViajeroById(id).viajesRealizados().map { it.toViajeDTO(getNombreConductor(it.idConductor)) }
        }
    }

    fun getNombreViajero(id: Long) = getViajeroById(id).nombreYApellido()

    fun getNombreConductor(id: Long) = getConductorById(id).nombreYApellido()

    fun getViajesPendientesByUsuario(id: Long, esChofer: Boolean): List<ViajeDTO> {
        if (esChofer) throw BadRequestException("Inaccesible por conductores")
        return getViajeroById(id).viajesPendientes().map { it.toViajeDTO(getNombreConductor(it.idConductor)) }
    }

    @Transactional
    fun agregarAmigo(idViajero: Long, idAmigo: Long): AmigoDTO {
        val viajero = getViajeroById(idViajero)
        val amigo = getViajeroById(idAmigo)

        viajero.agregarAmigo(amigo)
        viajeroRepository.update(viajero)
        return amigo.toAmigoDTO()
    }

    @Transactional
    fun eliminarAmigo(idViajero: Long, idAmigo: Long) {
        val viajero = getViajeroById(idViajero)
        val amigo = getViajeroById(idAmigo)
        viajero.eliminarAmigo(amigo)
        viajeroRepository.update(viajero)
    }

    fun getViajesConductorFiltrados(id: Long, filtroDTO: FiltroDTO): List<ViajeDTO> {
        val conductor = getConductorById(id)
        val viajesPendientes = conductor.viajesPendientes()
        return viajesPendientes.filter {
            (filtroDTO.usernameViajero.isBlank() || getViajeroById(it.idViajero).username.contains(filtroDTO.usernameViajero, ignoreCase = true)) &&
                    (filtroDTO.origen.isBlank() || it.origen.contains(filtroDTO.origen, ignoreCase = true)) &&
                    (filtroDTO.destino.isBlank() || it.destino.contains(filtroDTO.destino, ignoreCase = true)) &&
                    (filtroDTO.cantidadDePasajeros == 0 || it.cantidadDePasajeros == filtroDTO.cantidadDePasajeros)
        }.map { it.toViajeDTO(getNombreViajero(it.idViajero)) }
    }

    fun getViajerosParaAgregarAmigo(id: Long, query: String): List<AmigoDTO> {
        val amigos = getViajeroById(id).amigos
        return getViajeros().filter { !amigos.contains(it) &&
                            (it.nombre.contains(query, ignoreCase = true) ||
                            it.apellido.contains(query, ignoreCase = true) ||
                            it.username.contains(query, ignoreCase = true))
        }.map { it.toAmigoDTO() }
    }

    @Transactional
    fun calificarViaje(id: Long, calificacion: CalificacionDTO) {
        val viaje = viajeService.getViajeById(calificacion.idViaje)
        val viajero = getViajeroById(id)
        val conductor = getConductorById(viaje.idConductor)
        validarPuedeCalificar(viajero, viaje)
        val comentario = comentarioService.calificar(calificacion, viaje)
        viajero.agregarComentario(comentario)
        conductor.agregarComentario(comentario)
        viajeroRepository.update(viajero)
        conductorRepository.update(conductor)
    }

    fun validarPuedeCalificar(usuario: Viajero, viaje: Viaje) {
        validarEsElViajero(usuario, viaje)
        validarNoCalificado(usuario, viaje)
    }

    fun validarEsElViajero(usuario: Viajero, viaje: Viaje) {
        if (usuario.id != viaje.idViajero) throw BadRequestException("No se puede calificar un viaje en el que no participaste.")
    }

    fun validarNoCalificado(usuario: Viajero, viaje: Viaje) {
        if (!usuario.viajes.contains(viaje)) throw BadRequestException("No se puede calificar el mismo viaje más de una vez.")
    }
}