package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Departamento;

@Repository
public interface IDepartamentoRepository extends CrudRepository<Departamento, Long>{

}
