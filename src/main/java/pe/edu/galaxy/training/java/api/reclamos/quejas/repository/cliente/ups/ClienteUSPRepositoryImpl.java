package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente.ups;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;


@Slf4j
@Repository
public class ClienteUSPRepositoryImpl implements IClienteUSPRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Cliente> findByNumDocUSP(String numDoc) {
		StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.buscarXNUMDOC");
		spq.setParameter("P_NUM_DOC", numDoc);
		log.info("ruc",numDoc);
		
		Cliente cliente=null;
		
		if (spq.execute()) {
			List<Cliente> lst = spq.getResultList();
			log.info("lst",lst);
			
			if (lst.isEmpty() || lst.size() == 0) {
				return Optional.empty();
			}
			
			cliente=lst.get(0);
			
			log.info(cliente.toString());
			
			return Optional.of(cliente);
		}
		return Optional.empty();
	}

}
