package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.api.reclamos.quejas.dto.response.CustomResponse;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Queja;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.servicio.IQuejaService;

@RestController
@RequestMapping(path="/servicio/queja")
public class QuejaController  extends GenericController {

	@Autowired
	private IQuejaService quejaService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Queja> optQueja = quejaService.findAll();
			if (optQueja.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(optQueja);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(path= "/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>  findById(@PathVariable("id") Long id) {
		try {
			Queja optQueja = quejaService.findById(id).orElse(null);
			
			if (optQueja == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("El servicio con el id ingresado no existe").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("El servicio con el id ingresado si existe")
							.data(optQueja).build());
					
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
