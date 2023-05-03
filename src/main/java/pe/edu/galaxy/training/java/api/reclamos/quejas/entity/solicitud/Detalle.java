package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Detalle")
@Table(name="DETALLES")
@EqualsAndHashCode
public class Detalle implements Serializable{

	private static final long serialVersionUID = -5467458619551056586L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDetalle")
    @SequenceGenerator(sequenceName = "SEQ_DETALLE", allocationSize = 1, name = "seqDetalle")
	@Column(name="ID_TIPOSOLICITUD")
	private Long id;
	
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="DESCRIPCION_ESTADO")
	private String descripcionEstado;
	
	@JsonIgnoreProperties({"detalles", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOLICITUD", nullable = false)
	private Solicitud solicitud;
	
	@Column(name="CONDICION")
	private String condicion = "1";
	
}
