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
        if(usuario.isNotEmpty()) {
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

    fun getComentarios(id :Long) = getConductorById(id).comentarios.map { it.toComentarioDTO() }

    fun getCalificacionChofer(id: Long) = getConductorById(id).calificacion()


    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO) =
        getConductores().filter { it.disponible(busquedaDTO.fecha, busquedaDTO.duracion) }.map { it.toConductorDTO(busquedaDTO.cantidadDePasajeros, busquedaDTO.duracion) }


    @Transactional
    fun contratarViaje(viajeDTO : ViajeDTO) {
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
        }else {
             getViajeroById(id).toPerfilDTO()
        }
    }

    fun getViajesByUsuario(id :Long) = viajeroRepository.findById(id)

}