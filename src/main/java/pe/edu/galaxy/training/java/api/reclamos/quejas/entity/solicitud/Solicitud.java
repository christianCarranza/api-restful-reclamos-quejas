package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente.Cliente;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion.Distrito;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Producto;
import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio.Queja;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Solicitud")
@Table(name="SOLICITUDES")
@EqualsAndHashCode
public class Solicitud implements Serializable{

	private static final long serialVersionUID = -5467458619551056586L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSolicitud")
    @SequenceGenerator(sequenceName = "SEQ_SOLICITUD", allocationSize = 1, name = "seqSolicitud")
	@Column(name="ID_SOLICITUD")
	private Long id;
	
	@JsonIgnoreProperties({"solicitudes", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "ID_DISTRITO", nullable = false)
	private Distrito distrito;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_PRODUCTO", nullable = true)
	private Producto producto;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_QUEJA", nullable = true)
	private Queja queja;
	
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name="TIPO_SOLICITUD")
	private String tipoSolicitud;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="CONDICION")
	private String condicion;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@JsonIgnoreProperties({"solicitud", "hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Detalle> detalles;
	
}













