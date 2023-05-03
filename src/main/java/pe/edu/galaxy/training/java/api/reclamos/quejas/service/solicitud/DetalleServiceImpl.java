package pe.edu.galaxy.training.java.api.reclamos.quejas.service.solicitud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Detalle;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.solicitud.IDetalleRepository;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

@Service
public class DetalleServiceImpl implements IDetalleService{

	@Autowired
	private IDetalleRepository detalleRepository;
	
	
	@Override
	public List<Detalle> findAll() throws ServiceException {
		try {
			return detalleRepository.findAll();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Detalle> findByLikeObject(Detalle detalle) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Detalle> findById(Long id) throws ServiceException {
		try {
			return detalleRepository.findById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Detalle add(Detalle detalle) throws ServiceException {
		try {
			return detalleRepository.save(detalle);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Detalle update(Detalle detalle) throws ServiceException {
		try {
			Optional<Detalle> optDetalle = detalleRepository.findById(detalle.getId());

			if (optDetalle.isPresent()) {
				Detalle retDetalle = optDetalle.get();
				BeanUtils.copyProperties(detalle, retDetalle);
				return detalleRepository.save(retDetalle);
			}
			throw new ServiceException("No existe el detalle con el id "+detalle.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Detalle delete(Long id) throws ServiceException {
		try {
			Optional<Detalle> optDetalle = detalleRepository.findById(id);

			if (optDetalle.isPresent()) {
				Detalle retDetalle = optDetalle.get();
				retDetalle.setCondicion("0");
				return detalleRepository.save(retDetalle);
			}
			throw new ServiceException("No existe la solicitud con el id "+id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
