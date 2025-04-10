package uberto.backendgrupo72025.Controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.*
import uberto.backendgrupo72025.Service.UsuarioService
import uberto.backendgrupo72025.Service.ViajeService


@RestController
@CrossOrigin("*")
class ViajeController(
    @Autowired val viajeService: ViajeService
) {

    @GetMapping("/perfil/viajes")
    @Operation(summary = "crear viaje")
    fun getAllViajes() = viajeService.getAllViajes()

    @PostMapping("/filtrar/{id}")
    @Operation(summary = "Devuelve los viajes pendientes filtrados para el home chofer")
    fun getViajesFiltrados(
        @PathVariable id: Long,
        @RequestBody filtroDTO: FiltroDTO
    ) = viajeService.getViajesConductorFiltrados(id, filtroDTO)

    @GetMapping("/viajesRealizados/{idUsuario}")
    @Operation(summary = "Devuelve los viajes realizados")
    fun getViajesRealizadosPorUsuario(
        @PathVariable idUsuario: Long,
        @RequestParam esChofer: Boolean
    ) = viajeService.getViajesRealizadosByUsuario(idUsuario, esChofer)

    @GetMapping("/viajesPendientes/{idUsuario}")
    @Operation(summary = "Devuelve los viajes Pendientes")
    fun getViajesPendientesPorUsuario(
        @PathVariable idUsuario: Long,
        @RequestParam esChofer: Boolean
    ) = viajeService.getViajesPendientesByUsuario(idUsuario, esChofer)
}