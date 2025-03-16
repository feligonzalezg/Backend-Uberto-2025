package uberto.backendgrupo72025.Service

import uberto.backendgrupo72025.Domain.Viajero
import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uberto.backendgrupo72025.DTO.BusquedaDTO
import uberto.backendgrupo72025.DTO.ViajeDTO
import uberto.backendgrupo72025.DTO.toViaje
import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Usuario
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Repository.UsuarioRepository
import java.time.LocalDateTime


@Service
class UsuarioService(val repoUsuario: UsuarioRepository) {


    fun getUsuarioLogin(user: UsuarioLoginDTO): Usuario? {
        val usuario = repoUsuario.findAll().filter { it.accesoUsuario(user) }
        if(usuario.isNotEmpty()) {
            return usuario.first()
        } else {
            throw RuntimeException("Los datos ingresados son incorrectos")
        }
    }

    fun getUsuarioById(id: Long) = repoUsuario.findById(id)

    fun comentariosRecibidos(id :Long) = getUsuarioById(id).comentarios

    fun calificacion(id :Long) = puntajeTotal(id).toDouble() / cantidadComentarios(id).toDouble()

    fun puntajeTotal(id: Long) = comentariosRecibidos(id).sumOf { it.puntaje }

    fun cantidadComentarios(id: Long) = comentariosRecibidos(id).size

//    fun getAmigos(id: Long) = getUsuarioById(id).amigos

    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO): List<Usuario> {
        val choferes = repoUsuario.findAll().filter { it.esChofer }
        return choferes.filter { it.disponible(busquedaDTO.fecha, busquedaDTO.duracion) }
    }

    fun contratarViaje(viajeDTO : ViajeDTO) {
        val viajero = getUsuarioById(viajeDTO.idViajero)
        val conductor = getUsuarioById(viajeDTO.idConductor)
        val viaje = viajeDTO.toViaje()


        //validarPuedeRealizarseViaje(viajero, conductor, viaje)
        //viajero.contratarViaje(viaje,conductor)
    }

    fun validarPuedeRealizarseViaje(viajero: Viajero, conductor: Conductor, viaje: Viaje) {

    }
}