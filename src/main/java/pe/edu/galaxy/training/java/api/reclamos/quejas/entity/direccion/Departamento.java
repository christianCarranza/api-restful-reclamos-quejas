package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.direccion;

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
@Entity(name="Departamento")
@Table(name = "DEPARTAMENTOS")
public class Departamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDepartamento")
    @SequenceGenerator(sequenceName = "SEQ_DEPARTAMENTO", allocationSize = 1, name = "seqDepartamento")
	@Column(name="ID_DEPARTAMENTO")
	private Long id;
	
	@Column(name="NOM_DEPARTAMENTO")
	private String nomDepartamento;
	
//	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Provincia> provincia;
	
}
