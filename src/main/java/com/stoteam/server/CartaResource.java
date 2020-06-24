/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/
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
import com.stoteam.carte.Bancomat;

@Path("/utente/{idIntestatario}/conto/{idConto}/carta")
public class CartaResource {

	Map<String, Bancomat> carte = new HashMap<>();
	Map<String, NewCookie> cookies = new HashMap<>();
	@Context HttpServletRequest req;
	@PathParam("idIntestatario") private int idIntestatario;
	@PathParam("idConto") private int idConto;

	@POST
	@ManagedAsync
	@Produces("application/json")
	@Consumes("application/json")
	public void createCarta(Bancomat carta, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				carta.salva();
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}
	@GET
	@Path("{idCarta}")
	@Produces("application/json")
	/**
	 * @param getCartaById - Ottiene Carta in base all'ID
	 * @return Carta
	 */
	public void getCartaById(@PathParam("idCarta") int idCarta, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				Bancomat b = new Bancomat().download(idCarta);
				carte.put(req.getRemoteAddr(), b);
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(carte.remove(req.getRemoteAddr())).build()));
	}
	@PUT
	@Path("{idCarta}")
	/**
	 * @param editCarta - Imposta Carta
	 * @return Carta
	 */
	public void editCarta(@PathParam("idCarta") int idCarta, @CookieParam("logged") Cookie logged, @Suspended final AsyncResponse ar, Bancomat newCarta) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {	
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				newCarta.update(idCarta);
			} else {
				System.out.println("id non valido");
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(carte.remove(req.getRemoteAddr())).build()));
	}

}
