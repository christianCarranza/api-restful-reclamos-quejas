package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente.ups;

import java.util.Optional;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;


public interface IClienteUSPRepository {

	Optional<Cliente> findByNumDocUSP(String numDoc);

}
