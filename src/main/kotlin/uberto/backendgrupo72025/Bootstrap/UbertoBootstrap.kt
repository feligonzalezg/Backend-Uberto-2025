package uberto.backendgrupo72025.Bootstrap


import Conductor
import Ejecutivo
import Moto
import Simple
import Viajero
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uberto.backendgrupo72025.Domain.Vehiculo
import uberto.backendgrupo72025.Repository.UsuarioRepository
import uberto.backendgrupo72025.Repository.VehiculoRepository

@Service
class UbertoBootstrap: InitializingBean {

    @Autowired(required = true)
    lateinit var usuarioRepository: UsuarioRepository

    @Autowired(required = true)
    lateinit var vehiculoRepository : VehiculoRepository


    override fun afterPropertiesSet() {
        crearVehiculos()
        crearChoferes()
        crearPasajeros()
        crearPasajeros()

    }

    val viajero1 = Viajero(
        nombreYApellido = "Juan Pérez",
        username = "juanp",
        contrasenia = "pass123",
        edad = 28,
        viajesRealizados = mutableListOf(),
        telefono = 123456789,
        saldo = 1500.50
    )

    val viajero2 = Viajero(
        nombreYApellido = "María González",
        username = "mariag",
        contrasenia = "secure456",
        edad = 34,
        viajesRealizados = mutableListOf(),
        telefono = 987654321,
        saldo = 2300.75
    )

    val viajero3 = Viajero(
        nombreYApellido = "Carlos López",
        username = "carlosl",
        contrasenia = "mypwd789",
        edad = 23,
        viajesRealizados = mutableListOf(),
        telefono = 456789123,
        saldo = 800.25
    )

    val viajero4 = Viajero(
        nombreYApellido = "Ana Martínez",
        username = "anam",
        contrasenia = "password1",
        edad = 41,
        viajesRealizados = mutableListOf(),
        telefono = 321654987,
        saldo = 3500.00
    )

     fun crearPasajeros() {
        usuarioRepository.save(viajero1)
        usuarioRepository.save(viajero2)
        usuarioRepository.save(viajero3)
        usuarioRepository.save(viajero4)
    }


    val autoSimple = Vehiculo("Toyota", "Corolla", "ABC123", 2018, Simple)
    val autoEjecutivo = Vehiculo("Ford", "Focus", "DEF456", 2020, Ejecutivo)
    val autoSimple2 = Vehiculo("Honda", "Civic", "GHI789", 2016, Simple)
    val autoEjecutivo2 = Vehiculo("Chevrolet", "Cruze", "JKL012", 2019, Ejecutivo)
    val moto = Vehiculo("Yamaha", "FZ25", "MNO345", 2022, Moto)

    fun crearChoferes() {
        vehiculoRepository.save(autoSimple)
        vehiculoRepository.save(autoSimple2)
        vehiculoRepository.save(autoEjecutivo)
        vehiculoRepository.save(autoEjecutivo2)
        vehiculoRepository.save(moto)

    }

    val conductor1 = Conductor(
        nombreYApellido = "Juan Pérez",
        username = "juanp123",
        edad = 35,
        contrasenia = "pass1234",
        viajesRealizados = mutableListOf(), // Lista vacía por ahora
        telefono = 123456789,
        autoEjecutivo,
        precioBaseDelViaje = 500
    )

    val conductor2 = Conductor(
        nombreYApellido = "María Gómez",
        username = "mariagomez",
        edad = 28,
        contrasenia = "secure5678",
        viajesRealizados = mutableListOf(), // Lista vacía por ahora
        telefono = 987654321,
        autoSimple,
        precioBaseDelViaje = 450
    )

    fun crearVehiculos(){
        usuarioRepository.save(conductor1)
        usuarioRepository.save(conductor2)
    }

}




