package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name="Distrito")
@Table(name = "DISTRITOS")
public class Distrito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDistrito")
    @SequenceGenerator(sequenceName = "SEQ_DISTRITO", allocationSize = 1, name = "seqDistrito")
	@Column(name="ID_DISTRITO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_PROVINCIA", nullable=false)
	private Provincia provincia;
	
	@Column(name="NOM_DISTRITO")
	private String nomDistrito;
	
}




