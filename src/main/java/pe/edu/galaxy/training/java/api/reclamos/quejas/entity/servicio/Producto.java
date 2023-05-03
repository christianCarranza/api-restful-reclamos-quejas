package pe.edu.galaxy.training.java.api.reclamos.quejas.entity.servicio;

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
@Entity(name="Producto")
@Table(name = "PRODUCTOS")
public class Producto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProducto")
    @SequenceGenerator(sequenceName = "SEQ_PRODUCTO", allocationSize = 1, name = "seqProducto")
	@Column(name="ID_PRODUCTO")
	private Long id;
	
	@Column(name="DETALLE")
	private String detalle;
	
	@Column(name="MONTO")
	private Double monto;
	
	@Column(name="ESTADO")
	private String estado;

}





