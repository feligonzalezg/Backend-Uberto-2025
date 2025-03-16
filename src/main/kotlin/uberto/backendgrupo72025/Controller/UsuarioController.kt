package uberto.backendgrupo72025.Controller

import uberto.backendgrupo72025.Domain.Usuario
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.BusquedaDTO
import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.DTO.ViajeDTO

import uberto.backendgrupo72025.Service.UsuarioService


@RestController
@CrossOrigin("*")
class UsuarioController (@Autowired val userService : UsuarioService) {

    @GetMapping("/users")
    @Operation(summary = "Devuelve todos los usuarios")
    fun usuarios() = userService.getUsuarios()

    @PostMapping("/usuarioLogin")
    @Operation(summary = "Devuelve un usuario que coincida user y pass")
    fun postUsuarioLoggin(@RequestBody user: UsuarioLoginDTO) = userService.getUsuarioLogin(user)

    @GetMapping("/home/buscar")
    @Operation(summary = "Devuelve los choferes disponibles")
    fun getChoferesDisponiles(@RequestBody busquedaDTO: BusquedaDTO) = userService.getChoferesDisponibles(busquedaDTO)

    @GetMapping("/comentario/{id}")
    @Operation(summary = "Devuelve los comentarios por usuario")
    fun getComentariosPorUsuario(@PathVariable id:Long) = userService.comentariosRecibidos(id)

    @GetMapping("/puntaje/{id}")
    @Operation(summary = "Devuelve devuelve el puntaje por chofer")
    fun getPuntajePorChofer(@PathVariable id:Long) = userService.calificacion(id)

    @GetMapping("/amigos/{id}")
    @Operation(summary = "Devuelve devuelve la lista de amigues")
    fun getAmigos(@PathVariable id:Long) = userService.getAmigos(id)

    @PostMapping("/")
    @Operation(summary = "Contratar viaje")
    fun contratarViaje(@RequestBody viaje: ViajeDTO) = userService.contratarViaje(viaje)

}
