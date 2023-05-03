package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.solicitud;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.modelassembler.SolicitudModelAssembler;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.solicitud.ISolicitudService;

@RestController
@RequestMapping(path="/api/solicitud")
public class SolicitudController extends GenericController {

	private String api = "solicitud";
	
	private ISolicitudService solicitudService;
	private SolicitudModelAssembler solicitudModelAssembler;
	
	public SolicitudController(
			ISolicitudService solicitudService,
			SolicitudModelAssembler solicitudModelAssembler
			){
		this.solicitudService=solicitudService;
		this.solicitudModelAssembler=solicitudModelAssembler;
		
	}


//	@GetMapping("/find-all")
//	@ResponseBody
//	public ResponseEntity<?> findAll() {
//		try {
//			List<Solicitud> lst = solicitudService.findAll();
//			if (lst.isEmpty()) {
//				return super.getNotContent();
//			}
//			return ResponseEntity.ok(lst);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/find-all")
	@ResponseBody
	public CollectionModel<EntityModel<Solicitud>> findAll() {
		
		try {
			List<Solicitud> solicitudes = solicitudService.findAll();
		
			return solicitudModelAssembler.toCollectionModel(solicitudes);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public EntityModel<Solicitud> findById(@PathVariable("id") Long id) {
		try {
			Solicitud solicitud = solicitudService.findById(id).orElse(null);
			
			return solicitudModelAssembler.toModel(solicitud);
		} catch (Exception e) {
			return null;
		}
	}
	
//	@PostMapping
//	public ResponseEntity<?> add(@RequestBody @Validated Solicitud solicitud, BindingResult result) {
//		if (result.hasErrors()) {
//			return super.getErrors(result);
//		}
//		try {
//			Solicitud resSolicitud = solicitudService.add(solicitud);
//			if (Objects.isNull(resSolicitud)) {
//				super.getBadRequest(api);
//			}
//			return super.getCreated(resSolicitud, api);
//		} catch (Exception e) {
//			return super.getError(api);
//		}
//	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping
	public EntityModel<Solicitud> add(@RequestBody @Validated Solicitud solicitud, BindingResult result) {
		try {
			return solicitudModelAssembler.toModel(solicitudService.add(solicitud));
			
		} catch (Exception e) {
			return null;
		}
	}
	
//	@GetMapping("/by-condicion")
//	public ResponseEntity<?> findByCondicion(@RequestParam(name="condicion",defaultValue="") String condicion) {
//		try {
//			List<Solicitud> lst = solicitudService.findByCondicion(Solicitud.builder().condicion(condicion).build());
//			if (lst.isEmpty()) {
//				return super.getNotContent();
//			}
//			return ResponseEntity.ok(lst);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/by-condicion")
	public CollectionModel<EntityModel<Solicitud>> findByCondicion(@RequestParam(name="condicion",defaultValue="") String condicion) {
		try {
			List<Solicitud> lst = solicitudService.findByCondicion(Solicitud.builder().condicion(condicion).build());
			
			return solicitudModelAssembler.toCollectionModel(lst);
		} catch (Exception e) {
			return null;
		}
	}
	
	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Solicitud solicitud, BindingResult result) {
//		solicitud.setId(id);
//		if (result.hasErrors()) {
//			return super.getErrors(result);
//		}
//		try {
//			Solicitud resSolicitud = solicitudService.update(solicitud);
//			if (Objects.isNull(resSolicitud)) {
//				super.getBadRequestUpdate(api);
//			}
//			return super.getUpdated(resSolicitud, api);
//		} catch (Exception e) {
//			return super.getError(api);
//		}
//	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/{id}")
	public EntityModel<Solicitud> update(@PathVariable("id") Long id, @RequestBody Solicitud solicitud, BindingResult result) {
		
		try {
			solicitud.setId(id);
			return solicitudModelAssembler.toModel(solicitudService.update(solicitud));
			
		} catch (Exception e) {
			return null;
		}
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?>  delete(@PathVariable("id") Long id, @RequestBody Solicitud solicitud, BindingResult result) {
//		solicitud.setId(id);
//		if (result.hasErrors()) {
//			return super.getErrors(result);
//		}
//		try {
//			Boolean resSolicitud = solicitudService.delete(solicitud);
//			if (!resSolicitud) {
//				super.getBadRequestDelete(api);
//			}
//			return super.getDeleted(resSolicitud, api);
//		} catch (Exception e) {
//			return super.getError(api);
//		}
//	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable("id") Long id) {
		try {
			Solicitud resSolicitud = solicitudService.delete(id);
//			if (!resSolicitud) {
//				super.getBadRequestDelete(api);
//			}
			return super.getDeleted(resSolicitud, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	
}
