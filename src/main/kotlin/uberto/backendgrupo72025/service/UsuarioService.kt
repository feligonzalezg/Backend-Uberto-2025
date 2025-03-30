package uberto.backendgrupo72025.service

import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import uberto.backendgrupo72025.dto.*
import uberto.backendgrupo72025.domain.*
import uberto.backendgrupo72025.repository.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class UsuarioService(
    val vehiculoService: VehiculoService,
    val viajeService: ViajeService,
    val comentarioService: ComentarioService,
    val usuarioRepository: UsuarioRepository
) {

    fun getUsuarios() = usuarioRepository.findAll()

    fun getUsuarioById(idUsuario: Long) = usuarioRepository.findById(idUsuario)

    fun getViajeroById(id: Long) = usuarioRepository.findById(id).get() as Viajero

    fun getConductorById(id: Long) = usuarioRepository.findById(id).get() as Conductor

    fun getUsuarioLogin(user: UsuarioLoginDTO): LoginDTO {
        val usuario = getUsuarios().filter { it.accesoUsuario(user) }
        if (usuario.isNotEmpty()) {
            return usuario.first().toDTO1()
        } else {
            throw UnauthorizedException("Los datos ingresados son incorrectos")
        }
    }

    @Transactional
    fun actualizarUsuario(id: Long, usuarioDTO: UsuarioDTO): PerfilDTO {
        return if (usuarioDTO.esChofer) {
            actualizarChofer(id, usuarioDTO.toPerfilChoferDTO())
        } else {
            actualizarViajero(id, usuarioDTO.toPerfilViajeroDTO())
        }
    }

    @Transactional
    fun actualizarImagen(id: Long, imagen: String, esChofer: Boolean): String {
        lateinit var usuario: Usuario
        if (esChofer) {
            usuario = getConductorById(id)
            usuario.foto = imagen
            usuarioRepository.save(usuario)
        } else {
            usuario = getViajeroById(id)
            usuario.foto = imagen
            usuarioRepository.save(usuario)
        }
        return usuario.foto
    }

    fun validarSeRealizaronCambios(usuario: Usuario, usuarioDTO: PerfilDTO, param1: Number, param2: Number) {
        if (!seRealizaronCambios(usuario, usuarioDTO, param1, param2)) {
            throw BadRequestException("No se realizó ningún cambio.")
        }
    }

    fun seRealizaronCambios(usuario: Usuario, usuarioDTO: PerfilDTO, param1: Number, param2: Number) =
        usuario.nombre != usuarioDTO.nombre || usuario.apellido != usuarioDTO.apellido || param1 != param2

    // VIAJERO

    fun getAmigos(id: Long) = getViajeroById(id).amigos

    fun actualizarViajero(id: Long, viajeroDTO: PerfilViajeroDTO): PerfilViajeroDTO {
        val viajero = getViajeroById(id)
        validarSeRealizaronCambios(viajero, viajeroDTO, viajero.telefono, viajeroDTO.telefono)
        viajero.nombre = viajeroDTO.nombre
        viajero.apellido = viajeroDTO.apellido
        viajero.telefono = viajeroDTO.telefono
        usuarioRepository.save(viajero)
        return viajero.toPerfilDTO()
    }

    fun actualizarChofer(id: Long, choferDTO: PerfilChoferDTO): PerfilChoferDTO {
        val conductor = getConductorById(id)
        validarSeRealizaronCambiosConductor(conductor, choferDTO)
        val nuevoVehiculo = vehiculoService.actualizarVehiculo(conductor, choferDTO)
        conductor.nombre = choferDTO.nombre
        conductor.apellido = choferDTO.apellido
        conductor.precioBaseDelViaje = choferDTO.precioBase
        conductor.vehiculo = nuevoVehiculo
        conductor.validar()
        usuarioRepository.save(conductor)
        return conductor.toPerfilDTO()
    }

    fun validarSeRealizaronCambiosConductor(conductor: Conductor, choferDTO: PerfilChoferDTO) {
        if (!seRealizaronCambios(conductor, choferDTO, conductor.precioBaseDelViaje, choferDTO.precioBase) &&
            !vehiculoService.validarCambioVehiculo(conductor, choferDTO)
        )
            throw BadRequestException("No se realizaron cambios.")
    }

    fun getUsuarioPerfil(id: Long, esChofer: Boolean): PerfilDTO {
        return if (esChofer) {
            getConductorById(id).toPerfilDTO()
        } else {
            getViajeroById(id).toPerfilDTO()
        }
    }

    @Transactional
    fun agregarAmigo(idViajero: Long, idAmigo: Long): AmigoDTO {
        val viajero = getViajeroById(idViajero)
        val amigo = getViajeroById(idAmigo)
        viajero.agregarAmigo(amigo)
        usuarioRepository.save(viajero)
        return amigo.toAmigoDTO()
    }

    @Transactional
    fun eliminarAmigo(idViajero: Long, idAmigo: Long) {
        val viajero = getViajeroById(idViajero)
        val amigo = getViajeroById(idAmigo)
        viajero.eliminarAmigo(amigo)
        usuarioRepository.save(viajero)
    }

//    fun getViajerosParaAgregarAmigo(id: Long, query: String): List<AmigoDTO> {
//        val usuario = getViajeroById(id)
//        val amigos = usuario.amigos
//        return getViajeros().filter {
//            it.id!=id && !amigos.contains(it) &&
//                    (it.nombreYApellido().contains(query, ignoreCase = true) ||
//                            it.username.contains(query, ignoreCase = true))
//        }.map { it.toAmigoDTO() }
//    }

    fun cargarSaldo(id: Long, esChofer: Boolean, monto: Double): String {
        if (monto <= 0 || monto != monto.toLong().toDouble()) {
            throw BadRequestException("El monto debe ser un número entero positivo.")
        }
        val usuario = getViajeroById(id)
        if (esChofer) {
            throw BadRequestException("Los choferes no pueden cargar saldo")
        }
        usuario.agregarSaldo(monto)
        usuarioRepository.save(usuario)
        return "Saldo cargado exitosamente"
    }

//    fun getChoferesDisponibles(busquedaDTO: BusquedaDTO): List<ConductorDTO> {
//        return getConductores().filter {
//            conductorDisponible(
//                it.id,
//                LocalDateTime.parse(busquedaDTO.fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
//                busquedaDTO.duracion
//            )
//        }.map {
//            val calificacionByConductor = comentarioService.getCalificacionByConductor(it.id)
//            it.toConductorDTO(busquedaDTO.cantidadDePasajeros, busquedaDTO.duracion, calificacionByConductor)
//        }
//    }

    fun conductorDisponible(idConductor: Long, fechaNueva: LocalDateTime, duracion: Int) =
        !viajeService.getViajesByConductorId(idConductor).any { it.seSolapan(fechaNueva, duracion) }

    @Transactional
    fun contratarViaje(viajeDTO: ViajeDTO) {
        val viajero = getViajeroById(viajeDTO.idViajero)
        val conductor = getConductorById(viajeDTO.idConductor)
        validarPuedeRealizarseViaje(viajero, conductor.id, viajeDTO)
        val viaje = viajeService.crearViaje(viajeDTO, viajero, conductor)
        viajero.contratarViaje(viaje)
        usuarioRepository.save(viajero)
    }

    fun validarPuedeRealizarseViaje(viajero: Viajero, idConductor: Long, viajeDTO: ViajeDTO) {
        conductorDisponible(
            idConductor,
            LocalDateTime.parse(viajeDTO.fechaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            viajeDTO.duracion
        )
        viajero.validarSaldoSuficiente(viajeDTO.importe)
    }
}