package pe.edu.galaxy.training.java.api.reclamos.quejas.service.cliente.security;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Rol;
import pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente.IClienteRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private IClienteRepository clienteRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente= this.clienteRepository.findByEmail(email);
		if (cliente==null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		List<Rol> list = Collections.singletonList(cliente.getRol());

		List<GrantedAuthority> authorities = list
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
//		System.out.println("\nauthorities "+list);
		
		return new User(cliente.getEmail(), cliente.getClave(), authorities);		
	}

}
