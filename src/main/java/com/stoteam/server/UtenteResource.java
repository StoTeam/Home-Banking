package com.stoteam.server;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
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
import com.stoteam.attori.*;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

@Path("/utente")
public class UtenteResource {
	
	Map<String, Utente> users = new HashMap<>();
	Map<String, NewCookie> cookies = new HashMap<>();
	@Context HttpServletRequest req;
/**
 * Questo metodo attende una richiesta POST per creare 
 * un nuovo utente nel DB attraverso un CompletableFuture
 * @param utente
 * @param ar
 */
	@POST
	@ManagedAsync
	@Produces("application/json")
	public void createUser(Utente utente, @Suspended final AsyncResponse ar) {
		CompletableFuture<Object> fut = CompletableFuture.runAsync(() -> {
			utente.salva();
		}).thenApply(res -> ar.resume(Response.status(200).build()));
	}
	/**
	 * Questo metodo attende una richiesta POST su "utente/login" per effettuare 
	 * l'autenticazzione dell'utente attraverso un CompletableFuture
	 * @param ld
	 * @param ar
	 */
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
				cookies.put(req.getRemoteAddr(), new NewCookie("logged", "true " + ids[0] + " " + ids[1]));
				users.put(req.getRemoteAddr(), UtenteDao.getUtente(c, ids[0]));
			}
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).thenApplyAsync(res -> ar.resume(Response.seeOther(URI.create("" + users.get(req.getRemoteAddr()).getId())).status(200).cookie(cookies.remove(req.getRemoteAddr())).build()));
	}
/**
 * Metodo che ascolta per una richiesta get su "utente/logout" e disconnette l'utente
 * sovrascrivendo il cookie d'itentificazione previa identificazione
 * @param login    | il Cookie
 * @param ar       | Risposta Asincrona
 */	
	@GET
	@Path("logout")
	public void logOut(@CookieParam("logged") Cookie login, @Suspended final AsyncResponse ar) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = login.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log) {
				cookies.put(req.getRemoteAddr(), new NewCookie("logged", "false 0 0"));
				System.out.println("CP TROVATO");	
			}
		}).thenApplyAsync(res -> ar.resume(Response.seeOther(URI.create("")).status(200).cookie(cookies.remove(req.getRemoteAddr())).build()));
	}
	/**
	 * Questo metodo attende una richiesta GET su "utente/{userId}"
	 * per richiedere i dati di un utente dal DB previa identificazione
	 * @param id
	 * @param ar
	 * @param logged
	 */
	@GET
	@Path("{userId}")
	@Produces("application/json")
	public void getUserById(@PathParam("userId") int id, @Suspended final AsyncResponse ar, @CookieParam("logged") Cookie logged){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && id == Integer.parseInt(cookieArr[1])) {
				Utente u = (Utente) new Utente().downloadPersona(id);
				users.put(req.getRemoteAddr(), u);
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(users.remove(req.getRemoteAddr()).toJson()).build()));
	}
	/**
	 * Questo metodo ascolta per una richiesta GET, è alternativo al metodo
	 * getUserById suddetto, recupera le info necessarie al recupero dei dati
	 * dell'utente dai cookie di login.
	 * @param logged
	 * @param ar
	 */
	@GET
	@Produces("application/json")
	public void getUserByCookie(@CookieParam("logged") Cookie logged, @Suspended final AsyncResponse ar){
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			int id = Integer.parseInt(cookieArr[1]);
			if(log) {
				Utente u = (Utente) new Utente().downloadPersona(id);
				users.put(req.getRemoteAddr(), u);
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(users.remove(req.getRemoteAddr()).toJson()).build()));	
	}
	/**
	 * Questo metodo modifica tutti i campi di un utente sul DB
	 * utilizzando i dati forniti all'API in newUser previa identificazione
	 * @param id
	 * @param logged
	 * @param ar
	 * @param newUser
	 */
	@PUT
	@Path("{userId}")
	public void editUtente(@PathParam("userId") int id, @CookieParam("logged") Cookie logged, @Suspended final AsyncResponse ar, Utente newUser) {
		CompletableFuture<Object> cf = CompletableFuture.runAsync(() -> {
			String[] cookieArr = logged.getValue().split(" ");
			boolean log = Boolean.parseBoolean(cookieArr[0]);
			if(log && id == Integer.parseInt(cookieArr[1])) {
				Utente u = (Utente) newUser.updatePersona(id);
				users.put(req.getRemoteAddr(), u);
			} else {
				System.out.println("id non valido");
			}
		}).thenApply(res -> ar.resume(Response.status(200).entity(users.remove(req.getRemoteAddr()).toJson()).build()));
	}
}
