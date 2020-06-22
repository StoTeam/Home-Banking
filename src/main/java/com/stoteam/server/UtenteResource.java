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
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

@Path("/utente")
public class UtenteResource {

	List<NewCookie> cookies = new ArrayList<>();

	@POST
	@ManagedAsync
	@Produces("application/json")
	public void createUser(Utente utente, @Suspended final AsyncResponse ar) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			Connection c = DbConnection.Connect();
			UtenteDao.UpUtente(c, utente);
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}).thenApply(res -> ar.resume(Response.seeOther(URI.create("login")).status(200).build()));
	}
	@POST
	@Path("login")
	@ManagedAsync
	@Produces("application/json")
	public synchronized void logIn(LoginData ld, @Suspended final AsyncResponse ar) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			Connection c = DbConnection.Connect();
			int[] ids = new int[2];
			ids[0] = UtenteDao.checkLogUtente(c, ld.getEmail(), ld.getPassword());
			ids[1] = UtenteDao.getIdIntestatario(c, ids[0]);
			if(ids[0] > 0) {
				cookies.add(new NewCookie("logged", "true"));
				cookies.add(new NewCookie("id", "" + ids[0]));
				cookies.add(new NewCookie("idInt", "" + ids[1]));
			}
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).thenApplyAsync(res -> ar.resume(Response.seeOther(URI.create("/")).status(200).cookie(cookies.remove(0), cookies.remove(0)).build()));
	}

	@GET
	@Path("logout")
	public void logOut(@CookieParam("logged") Cookie login, @Suspended final AsyncResponse ar) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			boolean log = Boolean.parseBoolean(login.getValue());
			if(log) {
				cookies.add(new NewCookie("logged", "false"));
				cookies.add(new NewCookie("id", "0"));
				cookies.add(new NewCookie("idInt", "0"));
				System.out.println("CP TROVATO");	
			}
		}).thenApplyAsync(res -> ar.resume(Response.seeOther(URI.create("")).status(200).cookie(cookies.remove(0)).build()));
	}
	@GET
	@Path("{userId}")
	@Produces("application/json")
	public void getUserById(@PathParam("userId") int id, @Suspended final AsyncResponse ar, @CookieParam("id") Cookie idAss, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			boolean log = Boolean.parseBoolean(logged.getValue());
			if(log && id == Integer.parseInt(idAss.getValue())) {
				Connection c = DbConnection.Connect();
				Utente u = UtenteDao.getUtente(c, id);
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}

	@GET
	@Produces("application/json")
	public void getUserByCookie(@CookieParam("id") Cookie idAss, @CookieParam("logged") Cookie logged, @Suspended final AsyncResponse ar){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {	
			boolean log = Boolean.parseBoolean(logged.getValue());
			int id = Integer.parseInt(idAss.getValue());
			if(log) {
				Connection c = DbConnection.Connect();
				Utente u = UtenteDao.getUtente(c, id);
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
	@Path("{userId}")
	public void editUtente(@PathParam("userId") int id, @CookieParam("logged") Cookie cp, @CookieParam("id") Cookie idAss, @Suspended final AsyncResponse ar, Utente newUser) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {	
			boolean log = Boolean.parseBoolean(cp.getValue());
			if(log && id == Integer.parseInt(idAss.getValue())) {
				Connection c = DbConnection.Connect();
				UtenteDao.updateUtente(c, id, newUser);
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
