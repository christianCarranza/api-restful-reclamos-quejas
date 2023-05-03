package pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Provincia;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.direccion.IProvinciaRepository;

@Service
public class ProvinciaServiceImpl implements IProvinciaService{

	@Autowired
	private IProvinciaRepository provinciaRepository;
	
	
	@Override
	public List<Provincia> findAll() {
		return (List<Provincia>) provinciaRepository.findAll();
	}

	@Override
	public Optional<Provincia> findById(Long id) {
		return provinciaRepository.findById(id);
	}

	@Override
	public List<Provincia> findByLikeNomDepat(Provincia provincia) {
		return provinciaRepository.findByLikeNomDepat(provincia.getDepartamento().getNomDepartamento());
	}
	
	@Override
	public List<Provincia> findByIdDepat(Provincia provincia) {
		return provinciaRepository.findByIdDepat(provincia.getDepartamento().getId());
	}
	
	
	//------>
	
//	@Override
//	public List<Provincia> findByLikeIdDep(Provincia provincia) {
//		System.out.println(provincia.getDepartamento().getId());
//		List<Provincia> lst = provinciaRepository.findByLikeIdDep(provincia.getDepartamento().getId());
//		return lst;
//	}

}
