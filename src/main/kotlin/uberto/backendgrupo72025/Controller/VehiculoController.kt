package uberto.backendgrupo72025.Controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.ViajeDTO
import uberto.backendgrupo72025.Service.UsuarioService
import uberto.backendgrupo72025.Service.VehiculoService
import uberto.backendgrupo72025.Service.ViajeService

@RestController
@CrossOrigin("*")
class VehiculoController(
    @Autowired val vehiculoService: VehiculoService
) {

    @GetMapping("/vehiculos")
    @Operation(summary = "Obtiene todos los vehiculos")
    fun getAllVehiculos() = vehiculoService.getAll()

}