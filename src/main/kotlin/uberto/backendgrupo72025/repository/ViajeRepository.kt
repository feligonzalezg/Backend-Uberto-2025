package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Viaje


@Component
class ViajeRepository: Repository<Viaje>() {
    override val items: MutableSet<Viaje> = mutableSetOf()

    override var nameEntityRepo: String = "Viaje"


}