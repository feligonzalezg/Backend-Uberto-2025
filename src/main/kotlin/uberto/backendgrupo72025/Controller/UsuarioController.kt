package uberto.backendgrupo72025.Controller

import uberto.backendgrupo72025.Domain.Usuario
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.*

import uberto.backendgrupo72025.Service.UsuarioService


@RestController
@CrossOrigin("*")
class UsuarioController(@Autowired val userService: UsuarioService) {

    @GetMapping("/users")//test
    @Operation(summary = "Devuelve todos los usuarios")
    fun usuarios() = userService.getUsuarios()

    @PostMapping("/usuarioLogin")
    @Operation(summary = "Devuelve un usuario que coincida user y pass")
    fun postUsuarioLoggin(@RequestBody user: UsuarioLoginDTO) = userService.getUsuarioLogin(user)

    @GetMapping("/perfil/{id}")
    @Operation(summary = "Devuelve los datos para el perfil")
    fun getUsuarioPerfil(
        @PathVariable id: Long,
        @RequestParam esChofer: Boolean
    ) = userService.getUsuarioPerfil(id, esChofer)

    @PostMapping("/home/buscar")
    @Operation(summary = "Devuelve los choferes disponibles")
    fun getChoferesDisponiles(@RequestBody busquedaDTO: BusquedaDTO) = userService.getChoferesDisponibles(busquedaDTO)

    @PostMapping("/filtrar/{id}")
    @Operation(summary = "Devuelve los viajes pendientes filtrados para el home chofer")
    fun getViajesFiltrados(@PathVariable id: Long,
                           @RequestBody filtroDTO: FiltroDTO) = userService.getViajesConductorFiltrados(id, filtroDTO)

    @GetMapping("/comentario/{id}")
    @Operation(summary = "Devuelve los comentarios por usuario")
    fun getComentariosPorUsuario(
        @PathVariable id: Long,
        @RequestParam esChofer: Boolean
    ) = userService.getComentarios(id, esChofer)

    @GetMapping("/puntaje/{id}")
    @Operation(summary = "Devuelve devuelve el puntaje por chofer")
    fun getPuntajePorChofer(@PathVariable id: Long) = userService.getCalificacionChofer(id)

    @GetMapping("/amigos/{id}")
    @Operation(summary = "Devuelve devuelve la lista de amigues")
    fun getAmigos(@PathVariable id: Long) = userService.getAmigos(id)

    @PostMapping("/confirmar")
    @Operation(summary = "Contratar viaje")
    fun contratarViaje(@RequestBody viaje: ViajeDTO) = userService.contratarViaje(viaje)

    @GetMapping("/viajesRealizados/{id}")
    @Operation(summary = "Devuelve los viajes realizados")
    fun getViajesRealizadosPorUsuario(
        @PathVariable id: Long,
        @RequestParam esChofer: Boolean
    ) = userService.getViajesRealizadosByUsuario(id, esChofer)

    @GetMapping("/viajesPendientes/{id}")
    @Operation(summary = "Devuelve los viajes Pendientes")
    fun getViajesPendientesPorUsuario(@PathVariable id: Long,
                                      @RequestParam esChofer: Boolean
    ) = userService.getViajesPendientesByUsuario(id, esChofer)

    @DeleteMapping("/eliminarAmigo/{userId}/{friendId}")
    @Operation(summary = "Elimina a un amigo de la lista de amigos del viajero")
    fun eliminarAmigo(
        @PathVariable userId: Long,
        @PathVariable friendId: Long
    ) = userService.eliminarAmigo(userId, friendId)

    @PutMapping("/agregarAmigo/{userId}/{friendId}")
    @Operation(summary = "agrega a un amigo de la lista de amigos del viajero")
    fun agregarAmigo(
        @PathVariable userId: Long,
        @PathVariable friendId: Long
    ) = userService.agregarAmigo(userId, friendId)
}


