package uberto.backendgrupo72025.Controller

import uberto.backendgrupo72025.Domain.Usuario
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.DTO.ConductorDTO
import uberto.backendgrupo72025.Service.ComentarioService

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

    @PostMapping("/confirmar")
    @Operation(summary = "Contratar viaje")
    fun contratarViaje(@RequestBody viaje: ViajeDTO) = userService.contratarViaje(viaje)

    @PostMapping("/home/buscar")
    @Operation(summary = "Devuelve los choferes disponibles")
    fun getChoferesDisponiles(@RequestBody busquedaDTO: BusquedaDTO) = userService.getChoferesDisponibles(busquedaDTO)

    @GetMapping("/amigos/{id}")
    @Operation(summary = "Devuelve devuelve la lista de amigues")
    fun getAmigos(@PathVariable id: Long) = userService.getAmigos(id)

    @DeleteMapping("/eliminarAmigo/{userId}/{friendId}")
    @Operation(summary = "Elimina a un amigo de la lista de amigos del viajero")
    fun eliminarAmigo(
        @PathVariable userId: Long,
        @PathVariable friendId: Long
    ) = userService.eliminarAmigo(userId, friendId)

    @PatchMapping("/actualizarUsuario/{id}")
    @Operation(summary = "Actualiza los datos del usuario")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuarioDTO: UsuarioDTO) =
        userService.actualizarUsuario(id, usuarioDTO)

    @GetMapping("/buscarAmigos/{id}")
    @Operation(summary = "Busca los usuarios para agregar como amigos")
    fun buscarAmigos(
        @PathVariable id: Long,
        @RequestParam query: String
    ) = userService.getViajerosParaAgregarAmigo(id, query)

    @PutMapping("/agregarAmigo/{userId}/{friendId}")
    @Operation(summary = "agrega a un amigo de la lista de amigos del viajero")
    fun agregarAmigo(
        @PathVariable userId: Long,
        @PathVariable friendId: Long
    ) = userService.agregarAmigo(userId, friendId)

    @PostMapping("/cargarSaldo/{id}")
    @Operation(summary = "Carga saldo a un usuario")
    fun cargarSaldo(
        @PathVariable id: Long,
        @RequestParam esChofer: Boolean,
        @RequestParam monto: Double
    ) = userService.cargarSaldo(id, esChofer, monto)

    @PatchMapping("/actualizarImagen/{id}")
    @Operation(summary = "Actualiza los datos del usuario")
    fun actualizarImagen(@PathVariable id: Long, @RequestParam esChofer: Boolean, @RequestParam imagen: String) =
        userService.actualizarImagen(id, imagen, esChofer)
}