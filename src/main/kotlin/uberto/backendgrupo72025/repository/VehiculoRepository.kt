package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Vehiculo

//interface VehiculoRepository  : CrudRepository<Vehiculo, Long> {
//
//}


@Component
class VehiculoRepository: Repository<Vehiculo>() {
    override val items: MutableSet<Vehiculo> = mutableSetOf()

    override var nameEntityRepo: String = "vehiculo"


}