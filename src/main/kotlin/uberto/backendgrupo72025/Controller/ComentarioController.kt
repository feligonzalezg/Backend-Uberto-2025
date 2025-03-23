package uberto.backendgrupo72025.Controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.CalificacionDTO
import uberto.backendgrupo72025.Service.ComentarioService

@RestController
@CrossOrigin("*")
class ComentarioController(
    @Autowired val comentarioService: ComentarioService
) {

    @GetMapping("/comentarios")
    @Operation(summary = "Obtiene todos los comentarios")
    fun getAllComentarios() = comentarioService.getAll()

    @GetMapping("/comentario/{id}")
    @Operation(summary = "Devuelve los comentarios por usuario")
    fun getComentariosPorUsuario(
        @PathVariable id: Long,
        @RequestParam esChofer: Boolean
    ) = comentarioService.getComentarios(id, esChofer)

    @PostMapping("/calificar/{idUsuario}")
    @Operation(summary = "Calificar un viaje realizado")
    fun calificarViaje(
        @PathVariable idUsuario: Long,
        @RequestBody calificacion: CalificacionDTO
    ) = comentarioService.calificarViaje(idUsuario, calificacion)

    @DeleteMapping("eliminarComentario/{idUsuario}/{idComentario}")
    @Operation(summary = "Elimina un comentario realizado")
    fun eliminarComentario(
        @PathVariable idUsuario: Long,
        @PathVariable idComentario: Long
    ) = comentarioService.eliminarComentario(idUsuario, idComentario)

    @GetMapping("/puntaje/{id}")
    @Operation(summary = "Devuelve devuelve el puntaje por chofer")
    fun getPuntajePorChofer(@PathVariable id: Long) = comentarioService.getCalificacionByConductor(id)
}