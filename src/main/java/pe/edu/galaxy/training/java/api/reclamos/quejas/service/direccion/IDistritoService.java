package pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion;


import java.util.List;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic.IGenericOpService;


public interface IDistritoService extends IGenericOpService<Distrito>{

	List<Distrito> findByLikeNomDist(Distrito distrito);
	
	List<Distrito> findByLikeIdDist(Distrito distrito);

}
