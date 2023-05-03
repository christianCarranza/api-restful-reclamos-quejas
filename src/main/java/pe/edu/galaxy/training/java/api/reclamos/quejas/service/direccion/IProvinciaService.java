package pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion;


import java.util.List;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Provincia;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic.IGenericOpService;

public interface IProvinciaService extends IGenericOpService<Provincia>{
	
	List<Provincia> findByLikeNomDepat(Provincia provincia);
	
	List<Provincia> findByIdDepat(Provincia provincia);
	
}
