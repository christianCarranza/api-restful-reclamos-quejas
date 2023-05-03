package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="TipoDocumento")
@Table(name = "TIPODOCUMENTOS")
public class TipoDocumento  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTipodocumento")
    @SequenceGenerator(sequenceName = "SEQ_TIPODOCUMENTO", allocationSize = 1, name = "seqTipodocumento")
	@Column(name="ID_TIPODOC")
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String Descripcion;
	
	@Column(name="LOGITUD")
	private int Longitud;
	
}







