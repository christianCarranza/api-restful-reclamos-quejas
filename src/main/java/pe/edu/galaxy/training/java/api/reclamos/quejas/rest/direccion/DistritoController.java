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
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Provincia;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.direccion.IDistritoService;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(path="/api/ubigeo/distrito")
public class DistritoController extends GenericController{

	@Autowired
	private IDistritoService distritoService;

	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Distrito> lst = distritoService.findAll();
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
			Distrito optDistrito = distritoService.findById(id).orElse(null);
			
			if (optDistrito == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("El producto con el id ingresado no existe").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("El producto con el id ingresado si existe")
							.data(optDistrito).build());
					
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/by-nomProv")
	public List<Distrito> findByLikeNomDist(@RequestParam(name="nomProvincia",defaultValue="") String nomProvincia) {
		Provincia prov = new Provincia();
		prov.setNomProvincia(nomProvincia);
		return distritoService.findByLikeNomDist(Distrito.builder().provincia(prov).build());
	}
	
//	@GetMapping("/by-idProv")
//	public List<Distrito> findByLikeIdDist(@RequestParam(name="idProvincia",defaultValue="") Long idProvincia) {
//		Provincia prov = new Provincia();
//		prov.setId(idProvincia);
//		return distritoService.findByLikeIdDist(Distrito.builder().provincia(prov).build());
//	}
	
	@GetMapping("/by-idProv/{idProv}")
	public List<Distrito> findByLikeIdDist(@PathVariable("idProv") Long idProvincia) {
		Provincia prov = new Provincia();
		prov.setId(idProvincia);
		return distritoService.findByLikeIdDist(Distrito.builder().provincia(prov).build());
	}
	
}




