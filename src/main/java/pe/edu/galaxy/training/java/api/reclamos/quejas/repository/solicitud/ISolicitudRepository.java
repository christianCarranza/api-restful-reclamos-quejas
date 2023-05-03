package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.solicitud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;


public interface ISolicitudRepository  extends JpaRepository<Solicitud, Long>{
	
	@Query("select p from Solicitud p where  Upper(p.condicion) like upper(:condicion)")	//JPQL
	List<Solicitud> findByCondicion(@Param("condicion") String condicion);
	
}
