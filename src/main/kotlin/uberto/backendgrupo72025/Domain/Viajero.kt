package uberto.backendgrupo72025.Domain

class Viajero(
    nombreYApellido: String,
    edad: Int,
    username: String,
    contrasenia: String,
    viajes: MutableList<Viaje> = mutableListOf(),
    telefono: Int,
    comentarios: MutableList<Comentario> = mutableListOf(),
    esChofer: Boolean,
    var saldo: Double,
    val amigos: MutableList<Viajero> = mutableListOf()
) : Usuario(nombreYApellido, edad, username, contrasenia, viajes, telefono, comentarios, esChofer) {


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