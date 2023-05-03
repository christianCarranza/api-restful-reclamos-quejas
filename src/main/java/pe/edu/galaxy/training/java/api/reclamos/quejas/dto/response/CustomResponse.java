package pe.edu.galaxy.training.java.api.reclamos.quejas.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.commons.CodeEnum;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

	private CodeEnum code;
	private Object message;
	private Object data;

}