package uberto.backendgrupo72025.Repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Usuario
import uberto.backendgrupo72025.Domain.Viajero
import uberto.backendgrupo72025.Domain.Conductor


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