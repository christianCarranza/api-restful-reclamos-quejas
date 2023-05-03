package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Provincia")
@Table(name = "PROVINCIAS")
public class Provincia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProvincia")
    @SequenceGenerator(sequenceName = "SEQ_PROVINCIA", allocationSize = 1, name = "seqProvincia")
	@Column(name="ID_PROVINCIA")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPARTAMENTO", nullable=false)
	private Departamento departamento;
	
	@Column(name="NOM_PROVINCIA")
	private String nomProvincia;
	
//	@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Distrito> distrito;
	
}
