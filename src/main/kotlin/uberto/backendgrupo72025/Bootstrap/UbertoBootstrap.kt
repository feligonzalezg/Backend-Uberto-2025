package uberto.backendgrupo72025.Bootstrap

import uberto.backendgrupo72025.Domain.*
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Repository.*
import java.time.LocalDateTime
import java.time.LocalDate

@Component
class UbertoBootstrap(
    val conductorRepository: ConductorRepository,
    val viajeroRepository: ViajeroRepository,
    val vehiculoRepository: VehiculoRepository,
    val comentarioRepository: ComentarioRepository,
    val viajeRepository: ViajeRepository
) : InitializingBean {

    override fun afterPropertiesSet() {
        crearUsuarios()
        crearVehiculos()
        crearChoferes()
        crearViajes()
        crearComentarios()
    }

    // VIAJEROS
    val viajero1 = Viajero("Juan", "Pérez", 28, "juanp", "pass123", 123456789, false, "", 1500.50, mutableListOf())
    val viajero2 = Viajero("María", "González", 34, "mariag", "secure456", 987654321, false, "", 2300.75, mutableListOf())
    val viajero3 = Viajero("Carlos", "López", 23, "carlosl", "mypwd789", 456789123, false, "", 800.25, mutableListOf())
    val viajero4 = Viajero("Ana", "Martínez", 41, "anam", "password1", 321654987, false, "",3500.00, mutableListOf())

    fun crearUsuarios() {
        listOf(viajero1, viajero2, viajero3, viajero4).forEach { viajeroRepository.save(it) }
    }

    // VEHICULOS
    final val vehiculoSimple = Vehiculo("Toyota", "Corolla", "ABC123", 2018)
    final val vehiculoEjecutivo = Vehiculo("Ford", "Focus", "DEF456", 2020)
    final val vehiculoMoto = Vehiculo("Yamaha", "FZ25", "MNO345", 2022)

    fun crearVehiculos() {
        listOf(vehiculoSimple, vehiculoEjecutivo, vehiculoMoto).forEach { vehiculoRepository.save(it) }
    }

    // CONDUCTORES
    val conductor1 = Simple("Luis", "Fernández", 35, "luisf", "pass1234", 111222333, true, "", vehiculoSimple, 400.0)
    val conductor2 = Ejecutivo("Elena", "Ramírez", 29, "elenaR", "secure789", 444555666, true, "", vehiculoEjecutivo, 600.0)
    val conductor3 = Moto("Pedro", "Sánchez", 40, "pedros", "mypass567", 777888999, true, "", vehiculoMoto, 300.0)

    fun crearChoferes() {
        listOf(conductor1, conductor2, conductor3).forEach { conductorRepository.save(it) }
    }

    // VIAJES
    fun crearViajes() {
        val viajes = mutableListOf<Viaje>()
        val conductores = listOf(conductor1, conductor2, conductor3)
        val viajeros = listOf(viajero1, viajero2, viajero3, viajero4)

        for (conductor in conductores) {
            repeat(8) {
                val viajero = viajeros.random()
                val fechaInicio = if (it % 2 == 0) LocalDateTime.now().minusDays(it.toLong()) else LocalDateTime.now().plusDays(it.toLong())

                viajes.add(
                    Viaje(
                        viajero = viajero,
                        conductor = conductor,
                        origen = "Ciudad ${it + 1}",
                        destino = "Destino ${it + 1}",
                        fechaInicio = fechaInicio,
                        cantidadDePasajeros = (1..3).random(),
                        duracion = (5..20).random(),
                        importe = conductor.importeViaje((1..3).random(), (5..20).random())
                    )
                )
            }
        }
        viajes.forEach { viajeRepository.save(it) }
    }

    // COMENTARIOS
    fun crearComentarios() {
        val viajesRealizados = viajeRepository.findAll().filter { it.fechaInicio.isBefore(LocalDateTime.now()) }
        val comentarios = mutableListOf<Comentario>()

        viajesRealizados.take(5).forEach {
            comentarios.add(
                Comentario(
                    viaje = it,
                    estrellas = (3..5).random(),
                    mensaje = "Comentario sobre el viaje de ${it.viajero.nombre} con ${it.conductor.nombre}.",
                    fecha = LocalDate.now()
                )
            )
        }
        comentarios.forEach { comentarioRepository.save(it) }
    }
}