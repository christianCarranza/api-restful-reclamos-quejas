package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageCustom {

	private Object data;	
	private Integer totalPages;

}
