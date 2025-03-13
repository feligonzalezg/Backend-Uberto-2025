package uberto.backendgrupo72025.Service

import Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uberto.backendgrupo72025.DTO.UsuarioLoginDTO
import uberto.backendgrupo72025.Repository.UsuarioRepository

@Service
class UsuarioService {

    @Autowired
    lateinit var repoUsuario : UsuarioRepository


    fun getUsuarioLogin(user: UsuarioLoginDTO): Usuario {
        val usuario = repoUsuario.findAll().filter { it.accesoUsuario(user) }
        if(usuario.isNotEmpty()) {
            return usuario.first()
        } else {
            throw RuntimeException("Los datos ingresados son incorrectos")
        }
    }


}