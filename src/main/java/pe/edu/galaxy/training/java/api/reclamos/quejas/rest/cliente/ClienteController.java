package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.cliente;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import pe.edu.galaxy.training.java.api.reclamos.quejas.dto.response.CustomResponse;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.generic.GenericController;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.cliente.IClienteService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(path="/api/cliente")
public class ClienteController extends GenericController {

	private String api = "cliente";
	
	@Autowired
	private IClienteService clienteService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/find-all")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		try {
			List<Cliente> lst = clienteService.findAll();
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(path= "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(clienteService.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/by-numDoc/{numDoc}")
	public ResponseEntity<?> findByNumDocUSP(@PathVariable("numDoc") String numDoc) {
		try {
			Optional<Cliente> optCliente = clienteService.findByNumDocUSP(numDoc);
			if (optCliente.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse.builder().code(CodeEnum.WARNING)
						.message("No existe cliente con el numero de documento ingresado").build());
			}

			return ResponseEntity.ok(
					CustomResponse.builder().code(CodeEnum.SUCCESS).message("Cliente con el numero de documento ingresado si existe")
							.data(optCliente.get()).build());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/by-nombre")
	public ResponseEntity<?> findByLikeNombre(@RequestParam(name="nombre",defaultValue="") String nombre) {
		try {
			List<Cliente> lst = clienteService.findByLikeObject(Cliente.builder().nombre(nombre).build());
			if (lst.isEmpty()) {
				return super.getNotContent();
			}
			return ResponseEntity.ok(lst);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return super.getErrors(result);
		}
		try {
			Cliente resCliente = clienteService.add(cliente);
			if (Objects.isNull(resCliente)) {
				super.getBadRequest(api);
			}
			return super.getCreated(resCliente, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Cliente cliente, BindingResult result) {
		cliente.setId(id);
		
		if (result.hasErrors()) {
			return super.getErrors(result);
		}
		try {
			Cliente resCliente = clienteService.update(cliente);
			if (Objects.isNull(resCliente)) {
				super.getBadRequestUpdate(api);
			}
			return super.getUpdated(resCliente, api);
		} catch (Exception e) {
			return super.getError(api);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public Cliente  delete(@PathVariable("id") Long id) {
		
		try {
			Cliente resCliente = clienteService.delete(id);
			if (resCliente!=null) {
				super.getBadRequestDelete(api);
			}
			return resCliente;
		} catch (Exception e) {
			return null;
		}
	}

}
