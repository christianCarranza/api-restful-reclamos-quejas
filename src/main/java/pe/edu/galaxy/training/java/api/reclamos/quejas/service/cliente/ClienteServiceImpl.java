package pe.edu.galaxy.training.java.api.reclamos.quejas.service.cliente;

import static pe.edu.galaxy.training.java.api.reclamos.quejas.util.QueryUtil.getLike;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente.IClienteRepository;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente.ups.IClienteUSPRepository;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IClienteUSPRepository clienteUSPRepository;

	
	@Override
	public List<Cliente> findAll() throws ServiceException {
		try {
			return clienteRepository.findAllCustom();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Cliente> findByLikeObject(Cliente cliente) throws ServiceException {
		try {
			List<Cliente> lst = clienteRepository.findByLikeNombre(getLike(cliente.getNombre()));
			return lst;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Cliente> findById(Long id) throws ServiceException {
		try {
			return clienteRepository.findById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Cliente add(Cliente cliente) throws ServiceException {
		try {
			//encriptar password
			String passwordEncoder = new BCryptPasswordEncoder().encode(cliente.getClave()); 
			cliente.setClave(passwordEncoder);
			
			return clienteRepository.save(cliente);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Cliente update(Cliente cliente) throws ServiceException {
		try {
			Optional<Cliente> optCliente = clienteRepository.findById(cliente.getId());

			if (optCliente.isPresent()) {
				Cliente retCliente = optCliente.get();
				BeanUtils.copyProperties(cliente, retCliente);
				return clienteRepository.save(retCliente);
			}
			throw new ServiceException("No existe cliente con el id "+cliente.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Cliente delete(Long id) throws ServiceException {
		try {
			Optional<Cliente> optCliente = clienteRepository.findById(id);

			if (optCliente.isPresent()) {
				Cliente retCliente = optCliente.get();
				retCliente.setEstado("0");
				return clienteRepository.save(retCliente);
			}
			throw new ServiceException("No existe cliente con el id "+id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Cliente> findByNumDocUSP(String numDoc) throws ServiceException {
		try {
			return clienteUSPRepository.findByNumDocUSP(numDoc);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Cliente findByEmail(String email) throws ServiceException {
		try {
			Cliente lst = clienteRepository.findByEmail(email);
			return lst;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
