package pe.edu.galaxy.training.java.api.reclamos.quejas.service.solicitud;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.generic.IGenericService;

public interface ISolicitudService extends IGenericService<Solicitud>{

	List<Solicitud> findByCondicion(Solicitud solicitud) throws ServiceException;
	
}
