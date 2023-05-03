package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.servicio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Queja;

@Repository
public interface IQuejaRepository extends CrudRepository<Queja, Long>{

}
