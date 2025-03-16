package uberto.backendgrupo72025.Service

import uberto.backendgrupo72025.Domain.Viajero
import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uberto.backendgrupo72025.DTO.BusquedaDTO
import uberto.backendgrupo72025.DTO.ViajeDTO
import uberto.backendgrupo72025.DTO.toViaje
import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Usuario
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Repository.*


@Service
class UsuarioService(
    val viajeroRepository: ViajeroRepository,
    val conductorRepository: ConductorRepository,
    val viajeRepository: ViajeRepository,
) {

    fun getConductores() = conductorRepository.findAll()

    fun getViajeros() = viajeroRepository.findAll()

    fun getUsuarios() = getConductores() + getViajeros()


    fun getUsuarioLogin(user: UsuarioLoginDTO): Usuario {
        val usuario = getUsuarios().filter { it.accesoUsuario(user) }
        if(usuario.isNotEmpty()) {
            return usuario.first()
        } else {
            throw RuntimeException("Los datos ingresados son incorrectos")
        }
    }

     // viajero
    fun getViajeroById(id: Long) = viajeroRepository.findById(id)

    fun getAmigos(id: Long) = getViajeroById(id).amigos


    //chofer
    fun getConductorById(id: Long) = conductorRepository.findById(id)

    fun comentariosRecibidos(id :Long) = getConductorById(id).comentarios

    fun cantidadComentarios(id: Long) = comentariosRecibidos(id).size
    fun puntajeTotal(id: Long) = comentariosRecibidos(id).sumOf { it.puntaje }

    fun calificacion(id :Long) = puntajeTotal(id).toDouble() / cantidadComentarios(id).toDouble()



    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO) =
        getConductores().filter { it.disponible(busquedaDTO.fecha, busquedaDTO.duracion) }


    @Transactional
    fun contratarViaje(viajeDTO : ViajeDTO) {
        val viajero = getViajeroById(viajeDTO.idViajero)
        val conductor = getConductorById(viajeDTO.idConductor)
        val viaje = viajeDTO.toViaje()


        //validarPuedeRealizarseViaje(viajero, conductor, viaje)
        //viajero.contratarViaje(viaje,conductor)

        viajeRepository.save(viaje)
        viajero.agregarViaje(viaje)
        conductor.agregarViaje(viaje)
        viajeroRepository.save(viajero)
        conductorRepository.save(conductor)

    }

    fun validarPuedeRealizarseViaje(viajero: Viajero, conductor: Conductor, viaje: Viaje) {

    }
}