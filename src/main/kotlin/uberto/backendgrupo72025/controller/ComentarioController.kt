package uberto.backendgrupo72025.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.dto.CalificacionDTO
import uberto.backendgrupo72025.service.ComentarioService
import uberto.backendgrupo72025.service.ViajeService

@RestController
@CrossOrigin("*")
class ComentarioController(
    @Autowired val comentarioService: ComentarioService,
    @Autowired val viajeService: ViajeService
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

//    @DeleteMapping("eliminarComentario/{idUsuario}/{idComentario}")
//    @Operation(summary = "Elimina un comentario realizado")
//    fun eliminarComentario(
//        @PathVariable idUsuario: Long,
//        @PathVariable idComentario: Long
//    ) = comentarioService.eliminarComentario(idUsuario, idComentario)

    @GetMapping("/puntaje/{id}")
    @Operation(summary = "Devuelve devuelve el puntaje por chofer")
    fun getPuntajePorChofer(@PathVariable id: Long) = comentarioService.getCalificacionByConductor(id)

//    @PostMapping("/calificar/{idUsuario}")
//    @Operation(summary = "Calificar un viaje realizado")
//    fun calificarViaje(
//        @PathVariable idUsuario: Long,
//        @RequestBody calificacion: CalificacionDTO
//    ) = viajeService.calificarViaje(idUsuario, calificacion)
}