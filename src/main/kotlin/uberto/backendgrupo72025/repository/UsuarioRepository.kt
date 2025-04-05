package uberto.backendgrupo72025.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.*
import java.time.LocalDateTime

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

    @EntityGraph(attributePaths = ["amigos"])
    fun findViajeroById(id: Long): Viajero

    @Query("""
    SELECT c 
    FROM Conductor c
    WHERE NOT EXISTS (
        SELECT 1 FROM Viaje v
        WHERE v.conductor.id = c.id
        AND (
            (v.fechaInicio < :nuevaFechaFin AND v.fechaFin > :nuevaFechaInicio)
            OR (v.fechaInicio < :nuevaFechaInicio AND v.fechaFin > :nuevaFechaInicio)
        )
    )
""")
    fun findConductoresDisponibles(nuevaFechaInicio: LocalDateTime, nuevaFechaFin: LocalDateTime): List<Conductor>

}