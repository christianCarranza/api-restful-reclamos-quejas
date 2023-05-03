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
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Producto;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.servicio.IProductoService;


@RestController
@RequestMapping(path="/servicio/producto")
public class ProductoController  extends GenericController {

	@Autowired
	private IProductoService productoService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Producto> lst = productoService.findAll();
//			log.info("listado", lst);
//			log.info("", lst.size());
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(path= "/{id}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>  findById(@PathVariable("id") Long id) {
		try {
			Producto optProducto = productoService.findById(id).orElse(null);
			
			if (optProducto == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("El producto con el id ingresado no existe").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("El producto con el id ingresado si existe")
							.data(optProducto).build());
					
		} catch (ServiceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
