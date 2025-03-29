package uberto.backendgrupo72025.repository

import org.springframework.data.repository.CrudRepository
import uberto.backendgrupo72025.domain.Viajero
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.Conductor

@Repository
interface ViajeroRepository  : CrudRepository<Viajero, Long> {

}

@Repository
interface ConductorRepository  : CrudRepository<Conductor, Long> {

}

