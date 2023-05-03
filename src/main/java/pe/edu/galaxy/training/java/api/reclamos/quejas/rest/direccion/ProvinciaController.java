package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.api.reclamos.quejas.dto.response.CustomResponse;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Departamento;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Provincia;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion.IProvinciaService;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(path="/api/ubigeo/provincia")
public class ProvinciaController extends GenericController{

	@Autowired
	private IProvinciaService provinciaService;

	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Provincia> lst = provinciaService.findAll();
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(path= "/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Provincia optProvincia = provinciaService.findById(id).orElse(null);
			
			if (optProvincia == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("La provincia con el id ingresado no existe").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("La provincia con el id ingresado si existe")
							.data(optProvincia).build());
					
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/by-nombre")
	public List<Provincia> findByLikeNomDepat(@RequestParam(name="nomDepartamento",defaultValue="") String nomDepartamento) {
		Departamento dpt = new Departamento();
		dpt.setNomDepartamento(nomDepartamento);
		return provinciaService.findByLikeNomDepat(Provincia.builder().departamento(dpt).build());
	}
	
//	@GetMapping("/by-idProv")
//	public List<Provincia> findByLikeIdDepat(@RequestParam(name="idDepartamento",defaultValue="") Long idDepartamento) {
//		Departamento dpt = new Departamento();
//		dpt.setId(idDepartamento);
//		return provinciaService.findByIdDepat(Provincia.builder().departamento(dpt).build());
//	}
	
	@GetMapping("/by-idProv/{idProv}")
	public List<Provincia> findByLikeIdDepat(@PathVariable("idProv") Long idDepartamento) {
		Departamento dpt = new Departamento();
		dpt.setId(idDepartamento);
		return provinciaService.findByIdDepat(Provincia.builder().departamento(dpt).build());
	}
	
}
