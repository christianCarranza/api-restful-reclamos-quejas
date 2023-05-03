package pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;


public interface IGenericService<T> {

	List<T> findAll() throws ServiceException;
	
	List<T> findByLikeObject(T t) throws ServiceException;
	
	Optional<T> findById(Long id) throws ServiceException;
	
	T add(T t) throws ServiceException;
	
	T update(T t)throws ServiceException;
	
	T delete(Long t)throws ServiceException;
	
}
