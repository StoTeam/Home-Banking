package com.stoteam.server;

import java.lang.reflect.Field;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;
import com.stoteam.attori.*;
import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

@Path("/utente/{idIntestatario}/conto")
public class ContoResource {

	List<NewCookie> cookies = new ArrayList<>();
	@PathParam("idIntestatario") private int idIntestatario;

	@POST
	@ManagedAsync
	@Produces("application/json")
	@Consumes("application/json")
	public void createConto(Conto conto, @CookieParam("idInt") Cookie idInt, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			boolean log = Boolean.parseBoolean(logged.getValue()) && Integer.parseInt(idInt.getValue()) == idIntestatario;
				System.out.println("verifiche in corso");
			if(log) {
				System.out.println("verifiche effettuate");
				Connection c = DbConnection.Connect();
				System.out.println("connesso");
				ContoDao.UpConto(c, conto, Integer.parseInt(idInt.getValue()));
				System.out.println("Conto uploadato");
				try {
					c.close();
					System.out.println("connessione chiusa");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
		System.out.println("Risposta applicata");
	}

	@GET
	@Path("{contoId}")
	@Produces("application/json")
	public void getContoById(@PathParam("contoId") int contoId, @Suspended final AsyncResponse ar, @CookieParam("idInt") Cookie idInt, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			boolean log = Boolean.parseBoolean(logged.getValue());
			if(log && idIntestatario == Integer.parseInt(idInt.getValue())) {
				Connection c = DbConnection.Connect();
				Conto co = ContoDao.getConto(c, contoId);
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}
	@PUT
	@Path("{contoId}")
	public void editUtente(@PathParam("contoId") int id, @CookieParam("logged") Cookie cp, @CookieParam("idInt") Cookie idInt, @Suspended final AsyncResponse ar, Conto newConto) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {	
			boolean log = Boolean.parseBoolean(cp.getValue());
			if(log && idIntestatario == Integer.parseInt(idInt.getValue())) {
				Connection c = DbConnection.Connect();
				ContoDao.updateConto(c, id, newConto);
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("id non valido");
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}

}
