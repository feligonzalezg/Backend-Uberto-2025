package uberto.backendgrupo72025.Controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import uberto.backendgrupo72025.DTO.ViajeDTO
import uberto.backendgrupo72025.DTO.toViaje
import uberto.backendgrupo72025.Service.ViajeService


@RestController
@CrossOrigin("*")
class Viajeontroller (@Autowired val viajeService: ViajeService) {


    @PostMapping("/crearViaje")
    @Operation(summary = "crear viaje")
    fun crearViaje(@RequestBody viajeDTO: ViajeDTO){

        viajeService.create(viajeDTO.toViaje())
    }

    @GetMapping("/viaje/{id}")
    @Operation(summary = "crear viaje")
    fun buscarViaje(@PathVariable id:Long){
        viajeService.buscar(id)
    }


}