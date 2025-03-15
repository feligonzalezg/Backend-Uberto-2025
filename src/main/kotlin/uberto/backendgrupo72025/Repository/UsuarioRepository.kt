package uberto.backendgrupo72025.Repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Usuario

@Component
class UsuarioRepository: Repository<Usuario>() {
    override val items: MutableSet<Usuario> = mutableSetOf()

    override var nameEntityRepo: String = "usuario"


}