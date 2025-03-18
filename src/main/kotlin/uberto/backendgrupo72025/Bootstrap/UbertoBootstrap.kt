package uberto.backendgrupo72025.Bootstrap
//
//
import Ejecutivo
import Moto
import Simple
import uberto.backendgrupo72025.Domain.Viajero
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Domain.Comentario
import uberto.backendgrupo72025.Domain.Viaje
import uberto.backendgrupo72025.Domain.Conductor
import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Repository.*
import java.time.LocalDate
import java.time.LocalDateTime

//
@Component
class UbertoBootstrap(
    val conductorRepository: ConductorRepository,
    val viajeroRepository: ViajeroRepository,
    val vehiculoRepository : VehiculoRepository,
    val comentarioRepository : ComentarioRepository,
    val viajeRepository : ViajeRepository
): InitializingBean {

    override fun afterPropertiesSet() {
        crearUsuarios()
        crearVehiculos()
        crearComentarios()
        crearChoferes()
        crearViaje()
        agregarComentario()
    }

    val viajero2 = Viajero(
        nombre = "María",
        apellido = "González",
        edad = 34,
        username = "mariag",
        contrasenia = "secure456",
        viajes = mutableListOf(),
        telefono = 987654321,
        comentarios = mutableListOf(), // Lista vacía por ahora
        esChofer = false,
        saldo = 2300.75,
        amigos = mutableListOf(), // Lista vacía por ahora
    )

    val viajero1 = Viajero(
        nombre = "Juan",
        apellido = "Pérez",
        username = "juanp",
        contrasenia = "pass123",
        edad = 28,
        viajes = mutableListOf(),
        telefono = 123456789,
        comentarios = mutableListOf(), // Lista vacía por ahora
        esChofer = false,
        saldo = 1500.50,
        amigos = mutableListOf(viajero2), // Lista vacía por ahora
    )

    val viajero3 = Viajero(
        nombre = "Carlos",
        apellido = "López",
        edad = 23,
        username = "carlosl",
        contrasenia = "mypwd789",
        viajes = mutableListOf(),
        telefono = 456789123,
        comentarios = mutableListOf(), // Lista vacía por ahora
        esChofer = false,
        saldo = 800.25,
        amigos = mutableListOf(), // Lista vacía por ahora
    )

    val viajero4 = Viajero(
        nombre = "Ana",
        apellido = "Martínez",
        edad = 41,
        username = "anam",
        contrasenia = "password1",
        viajes = mutableListOf(),
        telefono = 321654987,
        comentarios = mutableListOf(), // Lista vacía por ahora
        esChofer = false,
        saldo = 3500.00,
        amigos = mutableListOf(), // Lista vacía por ahora
    )

    fun crearUsuarios() {
        viajeroRepository.save(viajero1)
        viajeroRepository.save(viajero2)
        viajeroRepository.save(viajero3)
        viajeroRepository.save(viajero4)
    }

    // Vehiculos
    val autoSimple = Vehiculo("Toyota", "Corolla", "ABC123", 2018, Simple)
    val autoEjecutivo = Vehiculo("Ford", "Focus", "DEF456", 2020, Ejecutivo)
    val autoSimple2 = Vehiculo("Honda", "Civic", "GHI789", 2016, Simple)
    val autoEjecutivo2 = Vehiculo("Chevrolet", "Cruze", "JKL012", 2019, Ejecutivo)
    val moto = Vehiculo("Yamaha", "FZ25", "MNO345", 2022, Moto)

    fun crearVehiculos() {
        vehiculoRepository.save(autoSimple)
        vehiculoRepository.save(autoSimple2)
        vehiculoRepository.save(autoEjecutivo)
        vehiculoRepository.save(autoEjecutivo2)
        vehiculoRepository.save(moto)
    }

    // conductores
    val comentario1 = Comentario(
        autor = viajero1,
        puntaje = 5,
        mensaje = "Excelente viaje, muy cómodo y puntual. ¡Totalmente recomendado!",
        fecha = LocalDate.of(2025, 3, 10)
    )

    val comentario2 = Comentario(
        autor = viajero1,
        puntaje = 4,
        mensaje = "Buen viaje, aunque el servicio de comida podría mejorar.",
        fecha = LocalDate.of(2025, 3, 12)
    )

     val comentario3 = Comentario(
        autor = viajero2,
        puntaje = 3,
        mensaje = "Viaje aceptable, pero el tiempo de espera fue demasiado largo.",
        fecha = LocalDate.of(2025, 3, 14)
    )

    fun crearComentarios(){
        comentarioRepository.save(comentario1)
        comentarioRepository.save(comentario2)
        comentarioRepository.save(comentario3)
    }

    val conductor1 = Conductor(
        nombre = "Juan",
        apellido = "Pérez",
        edad = 35,
        username = "juanp123",
        contrasenia = "pass1234",
        viajes = mutableListOf(), // Lista vacía por ahora
        telefono = 123456789,
        comentarios = mutableListOf(), // Lista vacía por ahora
        esChofer = true,
        autoEjecutivo,
        precioBaseDelViaje = 500.0
    )

    val conductor2 = Conductor(
        nombre = "María",
        apellido = "Gómez",
        edad = 28,
        username = "mariagomez",
        contrasenia = "secure5678",
        viajes = mutableListOf(),
        telefono = 987654321,
        comentarios = mutableListOf(comentario1,comentario2),
        esChofer = true,
        autoSimple,
        precioBaseDelViaje = 450.0
    )


    fun crearChoferes(){
        conductorRepository.save(conductor1)
        conductorRepository.save(conductor2)
    }


    val viaje1 = Viaje(
        idConductor = conductor1.id,
        origen = "Salta",
        destino = "Tucumán",
        fechaInicio = LocalDateTime.now(),
        cantidadDePasajeros = 1,
        duracion = 10
    )
    fun crearViaje() {
        val conductorGuardado = conductorRepository.findById(conductor1.id)
        val viaje1 = Viaje(
            idConductor = conductorGuardado.id,
            origen = "Salta",
            destino = "Tucumán",
            fechaInicio = LocalDateTime.now().minusDays(1),
            cantidadDePasajeros = 1,
            duracion = 10
        )
        viajeRepository.save(viaje1)
        conductorGuardado.agregarViaje(viaje1)
        conductorRepository.update(conductorGuardado)
    }


    fun agregarComentario(){
        viajero1.agregarComentario(comentario1)
        viajeroRepository.update(viajero1)
    }

}
