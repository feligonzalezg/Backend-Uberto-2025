package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Comentario

//interface ComentarioRepository  : CrudRepository<Comentario, Long> {
//
//}


@Component
class ComentarioRepository: Repository<Comentario>() {
    override val items: MutableSet<Comentario> = mutableSetOf()

    override var nameEntityRepo: String = "Comentario"


}