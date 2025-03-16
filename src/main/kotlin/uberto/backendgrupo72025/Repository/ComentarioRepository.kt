package uberto.backendgrupo72025.Repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Comentario

//interface ComentarioRepository  : CrudRepository<Comentario, Long> {
//
//}


@Component
class ComentarioRepository: Repository<Comentario>() {
    override val items: MutableSet<Comentario> = mutableSetOf()

    override var nameEntityRepo: String = "Comentario"


}