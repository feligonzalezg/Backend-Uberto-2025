package uberto.backendgrupo72025.Bootstrap
//
//
import uberto.backendgrupo72025.Domain.Viajero
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uberto.backendgrupo72025.Repository.UsuarioRepository
//
@Service
class UbertoBootstrap: InitializingBean {

    @Autowired (required = false)
    lateinit var repoUsuario: UsuarioRepository

    override fun afterPropertiesSet() {
        crearUsuarios()
    }

    val viajero1 = Viajero(
        nombreYApellido = "Juan Pérez",
        username = "juanp",
        contrasenia = "pass123",
        edad = 28,
        viajesRealizados = mutableListOf(),
        telefono = 123456789,
        comentarios = mutableListOf(), // Lista vacía por ahora
        saldo = 1500.50,
        amigos = mutableListOf(), // Lista vacía por ahora
    )

    fun crearUsuarios(){
        repoUsuario.save(viajero1)
    }


}
//
//
//
//
