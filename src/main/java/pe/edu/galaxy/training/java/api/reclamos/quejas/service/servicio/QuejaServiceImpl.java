package pe.edu.galaxy.training.java.api.reclamos.quejas.service.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Queja;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.servicio.IQuejaRepository;

@Service
public class QuejaServiceImpl implements IQuejaService {

	@Autowired
	private IQuejaRepository quejaRepository;

	@Override
	public List<Queja> findAll() {
		return (List<Queja>) quejaRepository.findAll();
	}

	@Override
	public Optional<Queja> findById(Long id) {
		return quejaRepository.findById(id);
	}
	
	
}
