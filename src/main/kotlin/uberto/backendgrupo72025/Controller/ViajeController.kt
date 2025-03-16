package uberto.backendgrupo72025.Controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import uberto.backendgrupo72025.DTO.ViajeDTO
import uberto.backendgrupo72025.DTO.toViaje
import uberto.backendgrupo72025.Service.ViajeService


@RestController
@CrossOrigin("*")
class Viajeontroller (@Autowired val viajeService: ViajeService) {


    @PostMapping
    @Operation(summary = "crear viaje")
    fun crearViaje(viajeDTO: ViajeDTO){

        viajeService.create(viajeDTO.toViaje())
    }

}