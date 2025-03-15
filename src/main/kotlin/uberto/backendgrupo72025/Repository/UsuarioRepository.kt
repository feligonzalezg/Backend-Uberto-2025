package uberto.backendgrupo72025.Repository

import uberto.backendgrupo72025.Domain.Usuario
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository  : CrudRepository<Usuario, Long>{

}