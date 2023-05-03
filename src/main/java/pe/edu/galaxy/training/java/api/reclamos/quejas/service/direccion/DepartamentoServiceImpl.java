package pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Departamento;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion.IDepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

	@Autowired
	private IDepartamentoRepository departamentoRepository;
	
	
	@Override
	public List<Departamento> findAll() {
		return (List<Departamento>) departamentoRepository.findAll();
	}

	@Override
	public Optional<Departamento> findById(Long id) {
		return departamentoRepository.findById(id);
	}

}
