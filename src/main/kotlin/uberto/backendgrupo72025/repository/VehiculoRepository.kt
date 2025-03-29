package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Vehiculo

@Repository
interface VehiculoRepository  : CrudRepository<Vehiculo, Long> {

}
