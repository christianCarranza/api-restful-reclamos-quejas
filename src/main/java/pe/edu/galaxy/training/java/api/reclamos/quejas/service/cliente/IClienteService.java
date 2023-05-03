package pe.edu.galaxy.training.java.api.reclamos.quejas.service.cliente;


import java.util.Optional;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente>{

	Optional<Cliente> findByNumDocUSP(String numDoc) throws ServiceException;
	
	Cliente findByEmail(String email) throws ServiceException;
	
}
