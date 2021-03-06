/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/
package com.stoteam.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.stoteam.attori.*;
import com.stoteam.carte.Bancomat;
import com.stoteam.conto.Conto;
import com.stoteam.dao.CartaDao;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.MovimentoDao;
import com.stoteam.movimenti.*;

@Path("/utente/{idIntestatario}/conto/{idConto}/movimenti")
public class MovimentoResource {

	Map<String, Movimento> movimenti = new HashMap<>();
	Map<String, NewCookie> cookies = new HashMap<>();
	@Context HttpServletRequest req;
	@PathParam("idIntestatario") private int idIntestatario;
	@PathParam("idConto") private int idConto;

	@POST
	@ManagedAsync
	@Produces("application/json")
	@Consumes("application/json")
	/**
	 * @param createMovimento - Crea il movimento bancario
	 * @return Movimento
	 */
	public void createMovimento(Movimento movimento, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				movimento.esegui();
				movimento.salva();				
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}
	@GET
	@Path("{idMovimento}")
	@Produces("application/json")
	/**
	 * @param getCartaById - Ottiene Carta da ID
	 * @return Carta
	 */
	public void getCartaById(@PathParam("idMovimento") int idMovimento, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && idIntestatario == Integer.parseInt(cookieArr[2])) {
				Movimento u = Movimento.download(idMovimento);
				movimenti.put(req.getRemoteAddr(), u);
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(movimenti.remove(req.getRemoteAddr()).toJson()).build()));
	}
}
