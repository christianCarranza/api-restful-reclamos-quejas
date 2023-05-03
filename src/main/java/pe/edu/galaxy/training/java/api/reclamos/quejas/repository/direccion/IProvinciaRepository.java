package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Provincia;

@Repository
public interface IProvinciaRepository extends CrudRepository<Provincia, Long>{

	@Query("select p from Provincia p where p.departamento.nomDepartamento = :nomDepartamento")	//JPQL
	List<Provincia> findByLikeNomDepat(@Param("nomDepartamento") String nomDepartamento);
	
	@Query("select p from Provincia p where p.departamento.id = :idDepartamento")	//JPQL
	List<Provincia> findByIdDepat(@Param("idDepartamento") Long idDepartamento);
	
}