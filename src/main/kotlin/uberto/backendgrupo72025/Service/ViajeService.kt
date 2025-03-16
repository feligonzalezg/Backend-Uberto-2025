package uberto.backendgrupo72025.Service

import org.springframework.stereotype.Service
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Repository.ViajeRepository

@Service
class ViajeService(
    val viajeRepository:ViajeRepository
) {

    fun create(viaje : Viaje){
        viajeRepository.save(viaje)

    }

    fun buscar(id :Long) = viajeRepository.findById(id)
}