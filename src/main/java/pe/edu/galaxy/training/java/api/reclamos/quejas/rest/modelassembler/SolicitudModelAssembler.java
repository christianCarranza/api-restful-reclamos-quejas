package pe.edu.galaxy.training.java.api.reclamos.quejas.rest.modelassembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;

import pe.edu.galaxy.training.java.api.reclamos.quejas.entity.solicitud.Solicitud;
import pe.edu.galaxy.training.java.api.reclamos.quejas.rest.solicitud.SolicitudController;

@Service
public class SolicitudModelAssembler implements RepresentationModelAssembler<Solicitud, EntityModel<Solicitud>> {

	@Override
	public EntityModel<Solicitud> toModel(Solicitud solicitud) {
		
		EntityModel<Solicitud> model = EntityModel.of(
											solicitud,
									        linkTo(methodOn(SolicitudController.class).findById(solicitud.getId())).withRel("byId"),
									        linkTo(methodOn(SolicitudController.class).findAll()).withRel("solicitudes"),
									 		linkTo(methodOn(SolicitudController.class).delete(solicitud.getId())).withRel("delete"),
									 		linkTo(methodOn(SolicitudController.class).update(solicitud.getId(), solicitud, null)).withRel("update")
									 		);
//		if(solicitud.getDetalles().get(2).equals("Enviado")) {
//			
//			model.add(linkTo(methodOn(SolicitudController.class).findById(solicitud.getId())).withRel("cancelar"));
//		}
		
		
		return model;
		  
	}

	@Override
	public CollectionModel<EntityModel<Solicitud>> toCollectionModel(Iterable<? extends Solicitud> solicitudes) {
		//return RepresentationModelAssembler.super.toCollectionModel(entities);
		CollectionModel<EntityModel<Solicitud>> model = RepresentationModelAssembler.super.toCollectionModel(solicitudes);
		model.add(linkTo(methodOn(SolicitudController.class).findAll()).withSelfRel());
		 model.add(Link.of(linkTo(SolicitudController.class).toUriComponentsBuilder().build().toUriString()).withRel("add"));
		return model;
	}
}
