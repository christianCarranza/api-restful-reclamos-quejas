package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.cliente.IClienteService;
import pe.edu.galaxy.training.java.api.reclamos.quejas.service.exception.ServiceException;


@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IClienteService clienteService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Cliente cliente;
		try {
			cliente = clienteService.findByEmail(authentication.getName());
			
			Map<String, Object> info = new HashMap<>();
			info.put("nombre", cliente.getNombre());
			info.put("apellido", cliente.getApellidoPat());
			info.put("email", cliente.getEmail());
			
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
			
			return accessToken;
			
		} catch (ServiceException e) {
			return null;
		}
		
	}

}
