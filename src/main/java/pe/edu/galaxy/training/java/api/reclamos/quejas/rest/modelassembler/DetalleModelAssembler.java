package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Detalle;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.solicitud.DetalleController;


public class DetalleModelAssembler  implements RepresentationModelAssembler<Detalle, EntityModel<Detalle>> {

	@Override
	public EntityModel<Detalle> toModel(Detalle detalle) {
		EntityModel<Detalle> model = EntityModel.of(
				detalle,
		        linkTo(methodOn(DetalleController.class).findById(detalle.getId())).withRel("byId"),
		        linkTo(methodOn(DetalleController.class).findAll()).withRel("Detalles"),
		 		linkTo(methodOn(DetalleController.class).delete(detalle.getId())).withRel("delete"),
		 		linkTo(methodOn(DetalleController.class).update(detalle.getId(), detalle, null)).withRel("update")
		 		);
		//if(solicitud.getDetalles().get(2).equals("Enviado")) {
		//
		//model.add(linkTo(methodOn(SolicitudController.class).findById(solicitud.getId())).withRel("cancelar"));
		//}
		
		
		return model;
	}

}
