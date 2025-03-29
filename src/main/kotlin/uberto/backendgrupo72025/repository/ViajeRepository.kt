package uberto.backendgrupo72025.repository

import org.springframework.stereotype.Component
import uberto.backendgrupo72025.domain.Viaje


import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uberto.backendgrupo72025.domain.Vehiculo

@Repository
interface ViajeRepository  : CrudRepository<Viaje, Long> {

}
