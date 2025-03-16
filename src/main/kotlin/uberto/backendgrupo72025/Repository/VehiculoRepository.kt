package uberto.backendgrupo72025.Repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Vehiculo

//interface VehiculoRepository  : CrudRepository<Vehiculo, Long> {
//
//}


@Component
class VehiculoRepository: Repository<Vehiculo>() {
    override val items: MutableSet<Vehiculo> = mutableSetOf()

    override var nameEntityRepo: String = "vehiculo"


}