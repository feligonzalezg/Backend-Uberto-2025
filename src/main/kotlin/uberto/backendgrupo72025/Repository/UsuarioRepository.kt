package uberto.backendgrupo72025.Repository

import Usuario
import org.springframework.data.repository.CrudRepository


interface UsuarioRepository  : CrudRepository<Usuario, Long>{

}