package uberto.backendgrupo72025.Repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Viaje


@Component
class ViajeRepository: Repository<Viaje>() {
    override val items: MutableSet<Viaje> = mutableSetOf()

    override var nameEntityRepo: String = "Viaje"


}