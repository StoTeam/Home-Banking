package com.stoteam.server;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import com.stoteam.attori.*;

@Path("/utente")
public class UtenteResource {
	
	@POST
	@Produces("application/json")
	public Response createUser(Utente utente) {
		return Response.status(200).entity(utente.toJson()).build();
	}
	
}
