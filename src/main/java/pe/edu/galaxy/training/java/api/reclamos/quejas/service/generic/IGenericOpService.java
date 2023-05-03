package pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

public interface IGenericOpService<T> {

	List<T> findAll() throws ServiceException;

	Optional<T> findById(Long id) throws ServiceException;
	
}
