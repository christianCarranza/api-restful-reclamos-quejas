package pe.edu.galaxy.training.java.api.reclamos.quejas.service.solicitud;

import static pe.edu.galaxy.training.java.api.reclamos.quejas.util.QueryUtil.getLike;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.solicitud.ISolicitudRepository;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

@Service
public class SolicitudServiceImpl implements ISolicitudService {

	@Autowired
	private ISolicitudRepository solicitudRepository;

	@Override
	public List<Solicitud> findAll() throws ServiceException {
		try {
			return solicitudRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Solicitud> findByLikeObject(Solicitud t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Solicitud> findById(Long id) throws ServiceException {
		try {
			return solicitudRepository.findById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Solicitud add(Solicitud solicitud) throws ServiceException {
		try {
			solicitud.getDetalles().forEach(um -> {
				um.setSolicitud(solicitud);
			});

			return solicitudRepository.save(solicitud);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Solicitud update(Solicitud solicitud) throws ServiceException {
		try {
			Optional<Solicitud> optSolicitud = solicitudRepository.findById(solicitud.getId());

			if (optSolicitud.isPresent()) {
				Solicitud retSolicitud = optSolicitud.get();
				BeanUtils.copyProperties(solicitud, retSolicitud);
				if (retSolicitud.getCondicion().equalsIgnoreCase("Activo")) {
					return solicitudRepository.save(retSolicitud);
				} else {
					System.out.println(retSolicitud.getCondicion());
					throw new ServiceException(
							"La solicitud ya esta en proceso o ya ha terminado por lo que no puede modificarlo");
				}
			}
			throw new ServiceException("No existe la solicitud con el id " + solicitud.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Solicitud delete(Long id) throws ServiceException {
		try {
			Optional<Solicitud> optSolicitud = solicitudRepository.findById(id);

			if (optSolicitud.isPresent()) {
				Solicitud retSolicitud = optSolicitud.get();
				
				System.out.println("---> " + retSolicitud.getCondicion());
				
				if (retSolicitud.getCondicion().equalsIgnoreCase("Activo")) {
					retSolicitud.setEstado("0");
					return solicitudRepository.save(retSolicitud);
				}

			}
			throw new ServiceException("No existe la solicitud con el id " + id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Solicitud> findByCondicion(Solicitud solicitud) throws ServiceException {
		try {
			List<Solicitud> lst = solicitudRepository.findByCondicion(getLike(solicitud.getCondicion()));
			return lst;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

//	@Override
//	public List<Solicitud> findByCliente(Cliente cliente) throws ServiceException {
//		try {
//			List<Solicitud> lst = solicitudRepository.findByCliente(cliente);
//			return lst;
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
//	}
//
//	

}
