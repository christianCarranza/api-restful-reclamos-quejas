package pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion.IDistritoRepository;

@Service
public class DistritoServiceImpl implements IDistritoService{

	@Autowired
	private IDistritoRepository distritoRepository;
	
	@Override
	public List<Distrito> findAll() {
		return (List<Distrito>) distritoRepository.findAll();
	}

	@Override
	public Optional<Distrito> findById(Long id) {
		return distritoRepository.findById(id);
	}

	@Override
	public List<Distrito> findByLikeNomDist(Distrito distrito) {
		return distritoRepository.findByLikeNomDist(distrito.getProvincia().getNomProvincia());
	}

	@Override
	public List<Distrito> findByLikeIdDist(Distrito distrito) {
		return distritoRepository.findByLikeIdDist(distrito.getProvincia().getId());
	}

}
