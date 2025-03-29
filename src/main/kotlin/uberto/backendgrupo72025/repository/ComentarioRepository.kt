package uberto.backendgrupo72025.repository


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.Comentario

@Repository
interface ComentarioRepository  : CrudRepository<Comentario, Long> {

}

