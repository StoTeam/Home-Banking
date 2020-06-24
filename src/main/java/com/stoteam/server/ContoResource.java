package com.stoteam.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;
import com.stoteam.conto.Conto;

@Path("/utente/{idIntestatario}/conto")
public class ContoResource {

	Map<String, Conto> conti = new HashMap<>();
	Map<String, NewCookie> cookies = new HashMap<>();
	@Context HttpServletRequest req;
	@PathParam("idIntestatario") private int idIntestatario;

	@POST
	@ManagedAsync
	@Produces("application/json")
	@Consumes("application/json")
	public void createConto(Conto conto, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				conto.salva(cookieArr);
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}
	@GET
	@Path("{contoId}")
	@Produces("application/json")
	public void getContoById(@PathParam("contoId") int contoId, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				Conto u = new Conto().download(contoId);
				conti.put(req.getRemoteAddr(), u);
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(conti.remove(req.getRemoteAddr())).build()));
	}
	@PUT
	@Path("{contoId}")
	public void editConto(@PathParam("contoId") int id, @CookieParam("logged") Cookie logged, @Suspended final AsyncResponse ar, Conto newConto) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {	
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				newConto.update(id);
			} else {
				System.out.println("id non valido");
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(conti.remove(req.getRemoteAddr())).build()));
	}

}
