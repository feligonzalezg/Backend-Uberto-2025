package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Viajero
import uberto.backendgrupo72025.domain.Conductor


@Component
class ViajeroRepository: Repository<Viajero>() {
    override val items: MutableSet<Viajero> = mutableSetOf()

    override var nameEntityRepo: String = "Viajero"


}

@Component
class ConductorRepository: Repository<Conductor>() {
    override val items: MutableSet<Conductor> = mutableSetOf()

    override var nameEntityRepo: String = "Conductor"


}