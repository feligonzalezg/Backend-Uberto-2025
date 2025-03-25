package uberto.backendgrupo72025.Bootstrap
//
//

import uberto.backendgrupo72025.Domain.*
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.Repository.*
import java.time.LocalDate
import java.time.LocalDateTime

//
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
        crearComentarios()
        crearChoferes()
        crearViaje()
        crearViajesRealizados()
    }

    val viajero2 = Viajero(
        nombre = "María",
        apellido = "González",
        edad = 34,
        username = "mariag",
        contrasenia = "secure456",
        telefono = 987654321,
        esChofer = false,
        saldo = 2300.75,
        amigos = mutableListOf(),
        foto = ""
    )

    val viajero1 = Viajero(
        nombre = "Juan",
        apellido = "Pérez",
        username = "juanp",
        contrasenia = "pass123",
        edad = 28,
        telefono = 123456789,
        esChofer = false,
        saldo = 1500.50,
        amigos = mutableListOf(),
        foto = ""
    )

    val viajero3 = Viajero(
        nombre = "Carlos",
        apellido = "López",
        edad = 23,
        username = "carlosl",
        contrasenia = "mypwd789",
        telefono = 456789123,
        esChofer = false,
        saldo = 800.25,
        amigos = mutableListOf(),
        foto = ""
    )

    val viajero4 = Viajero(
        nombre = "Ana",
        apellido = "Martínez",
        edad = 41,
        username = "anam",
        contrasenia = "password1",
        telefono = 321654987,
        esChofer = false,
        saldo = 3500.00,
        amigos = mutableListOf(),
        foto = ""
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


    val conductor1 = Conductor(
        nombre = "Juan",
        apellido = "Pérez",
        edad = 35,
        username = "juanp123",
        contrasenia = "pass1234",
        telefono = 123456789,
        esChofer = true,
        foto = "",
        autoEjecutivo,
        precioBaseDelViaje = 500.0

    )

    val conductor2 = Conductor(
        nombre = "María",
        apellido = "Gómez",
        edad = 28,
        username = "mariagomez",
        contrasenia = "secure5678",
        telefono = 987654321,
        esChofer = true,
        foto = "",
        autoSimple,
        precioBaseDelViaje = 450.0
    )


    fun crearChoferes() {
        conductorRepository.save(conductor1)
        conductorRepository.save(conductor2)
    }


    val viaje1 = Viaje(
        viajero = viajero1,
        conductor = conductor1,
        origen = "Salta",
        destino = "Tucumán",
        fechaInicio = LocalDateTime.now(),
        cantidadDePasajeros = 1,
        duracion = 10
    )

    // comentarios
    val comentario1 = Comentario(
        viaje = viaje1,
        estrellas = 5,
        mensaje = "Excelente viaje, muy cómodo y puntual. ¡Totalmente recomendado!",
        fecha = LocalDate.of(2025, 3, 10)
    )

    val comentario2 = Comentario(
        viaje = viaje1,
        estrellas = 4,
        mensaje = "Buen viaje, aunque el servicio de comida podría mejorar.",
        fecha = LocalDate.of(2025, 3, 12)
    )

    val comentario3 = Comentario(
        viaje = viaje1,
        estrellas = 3,
        mensaje = "Viaje aceptable, pero el tiempo de espera fue demasiado largo.",
        fecha = LocalDate.of(2025, 3, 14)
    )

    fun crearComentarios() {
        comentarioRepository.save(comentario1)
        comentarioRepository.save(comentario2)
        comentarioRepository.save(comentario3)
    }

    fun crearViaje() {
        val viaje1 = Viaje(
            viajero = viajero1,
            conductor = conductor1,
            origen = "Salta",
            destino = "Tucumán",
            fechaInicio = LocalDateTime.of(2025, 3, 20, 10, 0, 0),
            cantidadDePasajeros = 1,
            duracion = 10
        )
        viajeRepository.save(viaje1)
        viajeroRepository.update(viajero1)
    }

    fun crearViajesRealizados() {

        val viajePasado1 = Viaje(
            viajero = viajero1,
            conductor = conductor2,
            origen = "Córdoba",
            destino = "Rosario",
            fechaInicio = LocalDateTime.of(2025, 1, 15, 8, 30, 0),
            cantidadDePasajeros = 1,
            duracion = 12,
            importe = conductor2.importeViaje(1, 12)
        )

        val viajePasado2 = Viaje(
            viajero = viajero1,
            conductor = conductor1,
            origen = "Mendoza",
            destino = "San Juan",
            fechaInicio = LocalDateTime.of(2025, 2, 20, 14, 0, 0),
            cantidadDePasajeros = 2,
            duracion = 8,
            importe = conductor1.importeViaje(2, 8)  // Calcular el importe
        )

        val viajePasado3 = Viaje(
            viajero = viajero1,
            conductor = conductor2,
            origen = "Buenos Aires",
            destino = "La Plata",
            fechaInicio = LocalDateTime.of(2025, 2, 10, 9, 15, 0),
            cantidadDePasajeros = 1,
            duracion = 5,
            importe = conductor2.importeViaje(1, 5)
        )

        viajeRepository.save(viajePasado1)
        viajeRepository.save(viajePasado2)
        viajeRepository.save(viajePasado3)
    }

}
