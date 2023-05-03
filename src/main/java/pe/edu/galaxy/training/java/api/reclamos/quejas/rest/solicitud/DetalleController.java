package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.solicitud;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Detalle;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.solicitud.IDetalleService;


@RestController
@RequestMapping(path="/api/detalle")
public class DetalleController extends GenericController{

private String api = "solicitud";
	
	@Autowired
	private IDetalleService detalleService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Detalle> lst = detalleService.findAll();
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(detalleService.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping
	public ResponseEntity<?> add(@RequestBody @Validated Detalle detalle, BindingResult result) {
		if (result.hasErrors()) {
			return super.getErrors(result);
		}
		try {
			Detalle resDetalle = detalleService.add(detalle);
			if (Objects.isNull(resDetalle)) {
				super.getBadRequest(api);
			}
			return super.getCreated(resDetalle, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable("id") Long id) {
		
		try {
			Detalle resDetalle = detalleService.delete(id);
//			if (!resDetalle) {
//				super.getBadRequestDelete(api);
//			}
			return super.getDeleted(resDetalle, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Detalle detalle, BindingResult result) {
		detalle.setId(id);
		
		if (result.hasErrors()) {
			return super.getErrors(result);
		}
		try {
			Detalle resDetalle = detalleService.update(detalle);
			if (Objects.isNull(resDetalle)) {
				super.getBadRequestUpdate(api);
			}
			return super.getUpdated(resDetalle, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	
}
