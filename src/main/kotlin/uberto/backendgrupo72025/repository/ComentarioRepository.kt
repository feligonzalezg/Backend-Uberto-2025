package uberto.backendgrupo72025.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.Comentario

@Repository
interface ComentarioRepository  : CrudRepository<Comentario, Long> {

    @Query("SELECT c FROM Comentario c " +
            "INNER JOIN Viaje v on v.id = c.viaje.id " +
            "where v.conductor.id = :id or v.viajero.id = :id and c.active = true")
    fun findByViajeroIdOrConductorId(id: Long): List<Comentario>

    fun countByViajeConductorId(conductorId: Long): Int

    @Query("SELECT sum(c.estrellas) FROM Comentario c\n" +
            "INNER JOIN Viaje v on v.id = c.viaje.id\n" +
            "where v.conductor.id = :idConductor and c.active = true\n")
    fun sumEstrellasByViajeConductorId(idConductor: Long): Int


}

