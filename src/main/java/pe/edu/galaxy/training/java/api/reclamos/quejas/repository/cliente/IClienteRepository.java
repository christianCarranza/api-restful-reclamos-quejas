package pe.edu.galaxy.training.java.api.reclamos.quejas.repository.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{


	@Query("select p from Cliente p where p.estado='1' and p.rol.id=2")
	List<Cliente> findAllCustom();
	
	@Query("select p from Cliente p where Upper(p.nombre) like upper(:nombre) and p.estado='1' and p.rol.id=1")
	List<Cliente> findByLikeNombre(@Param("nombre") String nombre);
	
	// seguridad
	
	@Query("select p from Cliente p where p.email=:email and p.estado='1'")
	Cliente findByEmail(@Param("email") String email);
}
