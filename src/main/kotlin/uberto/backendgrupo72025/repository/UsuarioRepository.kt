package uberto.backendgrupo72025.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import uberto.backendgrupo72025.domain.Viajero
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.Conductor
import uberto.backendgrupo72025.domain.Usuario
import uberto.backendgrupo72025.domain.Vehiculo

@Repository
interface ViajeroRepository  : CrudRepository<Viajero, Long> {

}

@Repository
interface ConductorRepository  : CrudRepository<Conductor, Long> {

}

@Repository
interface UsuarioRepository   : CrudRepository<Usuario, Long> {

//    @Query("select v.saldo from Viajero v where v.id= :id")
//    fun getSaldo(id: Long): Double

//    @Query("select v.vehiculo from Conductor v where v.id= :id")
//    fun getSaldo(id: Long): Vehiculo
}
