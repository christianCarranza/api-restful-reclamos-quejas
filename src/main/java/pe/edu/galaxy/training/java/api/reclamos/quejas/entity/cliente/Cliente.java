package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;


@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
					name="cliente.buscarXNUMDOC", 
					procedureName="PKG_CLIENTE.USP_LISTAR_X_NUM_DOC",
					resultClasses= Cliente.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,name="P_NUM_DOC", type=String.class )
						}					
				)
		}
		)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Cliente")
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
	@SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "seqCliente")
	@Column(name = "ID_CLIENTE")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false)
	private Rol rol;

	@ManyToOne
	@JoinColumn(name = "ID_TIPODOC", nullable = false)
	private TipoDocumento tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "ID_DISTRITO", nullable = false)
	private Distrito distrito;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO_PAT")
	private String apellidoPat;

	@Column(name = "APELLIDO_MAT")
	private String apellidoMat;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NUM_DOC")
	private String numDoc;

	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "CLAVE")
	private String clave;

	@Column(name = "CELULAR")
	private String celular;

	@Column(name = "ESTADO")
	private String estado="1";

	@JsonIgnoreProperties({ "cliente", "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Solicitud> solicitudes;
}
