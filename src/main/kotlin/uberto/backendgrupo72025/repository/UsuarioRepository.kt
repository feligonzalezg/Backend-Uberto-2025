package uberto.backendgrupo72025.repository

import org.springframework.data.repository.CrudRepository
import uberto.backendgrupo72025.domain.Viajero
import org.springframework.stereotype.Repository

@Repository
interface ViajeroRepository  : CrudRepository<Viajero, Long> {

}

