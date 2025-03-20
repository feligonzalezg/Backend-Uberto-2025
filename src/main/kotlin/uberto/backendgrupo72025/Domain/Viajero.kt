package uberto.backendgrupo72025.Domain

import java.time.LocalDateTime

class Viajero(
    nombre: String,
    apellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajes: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    esChofer: Boolean,
    var saldo: Double,
    val amigos: MutableList<Viajero> = mutableListOf()
) : Usuario(nombre, apellido, edad, username, contrasenia, viajes, telefono, comentarios, esChofer) {


    override fun validacionesPorUsuario() {
        validarSaldo()
    }

    fun agregarSaldo(saldoAAgregar: Double) {
        saldo += saldoAAgregar
    }

    private fun esSaldoValido() = saldo >= 0.0
    private fun validarSaldo() {
        if (!esSaldoValido()) throw RuntimeException("El saldo no puede ser menor a 0")
    }

    fun agregarAmigo(viajero: Viajero) {
        validarAmigoExistente(viajero)
        amigos.add(viajero)
    }

    fun eliminarAmigo(viajero: Viajero) {
        validarAmigoNoExistente(viajero)
        amigos.remove(viajero)
    }

    fun validarAmigoExistente(viajero: Viajero) {
        if (esAmigo(viajero)) throw BadRequestException("Ya es amigo")
    }

    fun validarAmigoNoExistente(viajero: Viajero) {
        if (!esAmigo(viajero)) throw BadRequestException("Amigo inexistente")
    }

    fun esAmigo(viajero: Viajero) = amigos.contains(viajero)

    fun validarSaldoSuficiente(costoDelViaje: Double) {
        if (!saldoSuficiente(costoDelViaje)) throw BadRequestException("Saldo insuficiente.")

    }
    fun saldoSuficiente(costoDelViaje: Double) = saldo >= costoDelViaje

    fun contratarViaje(viaje: Viaje) {
        descontarSaldo(viaje.importe)
        agregarViaje(viaje)
    }

    fun descontarSaldo(costoDelViaje: Double) { saldo -= costoDelViaje }
}