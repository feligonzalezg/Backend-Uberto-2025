package uberto.backendgrupo72025.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.service.VehiculoService

@RestController
@CrossOrigin("*")
class VehiculoController(
    @Autowired val vehiculoService: VehiculoService
) {

    @GetMapping("/vehiculos")
    @Operation(summary = "Obtiene todos los vehiculos")
    fun getAllVehiculos() = vehiculoService.getAll()

}