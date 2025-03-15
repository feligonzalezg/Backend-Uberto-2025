package uberto.backendgrupo72025.Domain

import uberto.backendgrupo72025.Domain.Usuario

class Viajero(
    nombreYApellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajesRealizados: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    var saldo: Double,
    val amigos: MutableList<Viajero> = mutableListOf()
) : Usuario(nombreYApellido, edad, username, contrasenia, viajesRealizados, telefono, comentarios) {

    override val esConductor: Boolean = false

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
        if (esAmigo(viajero)) throw RuntimeException("Ya es amigo")
    }

    fun validarAmigoNoExistente(viajero: Viajero) {
        if (!esAmigo(viajero)) throw RuntimeException("Amigo inexistente")
    }

    fun esAmigo(viajero: Viajero) = amigos.contains(viajero)


}