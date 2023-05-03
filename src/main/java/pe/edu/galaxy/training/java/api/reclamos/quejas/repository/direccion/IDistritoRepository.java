package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
@Repository
public interface IDistritoRepository extends CrudRepository<Distrito, Long> {
	
	@Query("select p from Distrito p where p.provincia.nomProvincia = :nomProvincia")	//JPQL
	List<Distrito> findByLikeNomDist(@Param("nomProvincia") String nomProvincia);
	
	@Query("select p from Distrito p where p.provincia.id = :idProvincia")	//JPQL
	List<Distrito> findByLikeIdDist(@Param("idProvincia") Long idProvincia);
	
}
