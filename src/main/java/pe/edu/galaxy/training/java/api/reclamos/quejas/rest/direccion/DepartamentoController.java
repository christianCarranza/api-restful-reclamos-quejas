package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.api.reclamos.quejas.dto.response.CustomResponse;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Departamento;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion.IDepartamentoService;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(path="/api/ubigeo/departamento")
public class DepartamentoController extends GenericController{

	@Autowired
	private IDepartamentoService departamentoService;

	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Departamento> lst = departamentoService.findAll();
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(path= "/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>  findById(@PathVariable("id") Long id) {
		try {
			Departamento optDepartamento = departamentoService.findById(id).orElse(null);
			
			if (optDepartamento == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("El departamento con el id ingresado no existe").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("El departamento con el id ingresado si existe")
							.data(optDepartamento).build());
					
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
