package uberto.backendgrupo72025.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.*

@Repository
interface ViajeroRepository  : CrudRepository<Viajero, Long> {

}

@Repository
interface ConductorRepository  : CrudRepository<Conductor, Long> {

}

@Repository
interface UsuarioRepository   : CrudRepository<Usuario, Long> {

    fun findByUsernameAndContrasenia(username: String, contrasenia: String): Usuario?

    @Query("""
    SELECT v FROM Viajero v 
    WHERE v.id != :id
    AND v.id NOT IN (
        SELECT a.id FROM Viajero u JOIN u.amigos a WHERE u.id = :id
    )
    AND (
        LOWER(CONCAT(v.nombre, ' ', v.apellido)) LIKE LOWER(CONCAT('%', :query, '%')) 
        OR LOWER(v.username) LIKE LOWER(CONCAT('%', :query, '%'))
    )
""")
    fun buscarViajerosNoAmigos(id: Long, query: String): List<Viajero>

    @Query("SELECT v FROM Viajero v LEFT JOIN FETCH v.amigos WHERE v.id = :id")
    fun findByIdWithAmigos(id: Long): Viajero



//    @Query("select v from Viajero v where v.id = :id")
//    fun findViajeroById(id: Long): Viajero
//
//    @Query("select c from Conductor c where c.id = :id")
//    fun findConductorById(id: Long): Conductor
//    @Query("select v.saldo from Viajero v where v.id= :id")
//    fun getSaldo(id: Long): Double

//    @Query("select v.vehiculo from Conductor v where v.id= :id")
//    fun getSaldo(id: Long): Vehiculo

//    @Query("""
//        SELECT c FROM Conductor c
//        WHERE NOT EXISTS (
//            SELECT v FROM Viaje v
//            WHERE v.conductor = c
//            AND (
//                (v.fechaInicio <= :nuevaFechaFin AND viejaFechaFin >= :fechaInicio)
//                OR (v.fechaInicio <= :fechaFinCalculada AND v.fechaFin >= :fechaInicio)
//            )
//        )
//        AND c.vehiculo.capacidad >= :cantidadPasajeros
//        AND c.esChofer = true
//    """)
//    fun findConductoresDisponibles(fechaInicio: String, nuevaFechaFin: String, viejaFechaFin: String, cantidadPasajeros: Int): List<Conductor>


}
