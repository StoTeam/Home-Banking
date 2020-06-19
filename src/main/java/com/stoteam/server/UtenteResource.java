package com.stoteam.server;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
	
//	@POST
//	@ManagedAsync
//	@Produces("application/json")
//	public void createUser(Utente utente, @Suspended final AsyncResponse ar) {
//		CompletableFuture fut = CompletableFuture.runAsync(() -> {
//			Connection c = DbConnection.Connect();
//			UtenteDao.UpUtente(c, utente);
//			try {
//				c.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}).thenApply(res -> ar.resume(Response.status(200).build()));
//	}
	@POST
	@Produces("application/json")
	public Response createUser(Utente utente) {
		Connection c = DbConnection.Connect();
		UtenteDao.UpUtente(c, utente);
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(200).build();
	}
	
	@POST
	@Path("login")
	@Produces("application/json")
	public Response logIn(LoginData ld) {
		Connection c = DbConnection.Connect();
		int id = UtenteDao.checkLogUtente(c, ld.getEmail(), ld.getPassword());
		if(id > 0) {
			NewCookie login = new NewCookie("logged", "true");
			return Response.status(200).cookie(login).entity(UtenteDao.getUtente(c, id).toJson()).build();
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(404).build();
	}
	
	@GET
	@Path("logout")
	public Response logOut(@Context HttpServletRequest req) {
		boolean log = Boolean.parseBoolean((String) req.getAttribute("logged"));
		if(log) {
			NewCookie login = new NewCookie("logged", "false");
			return Response.status(200).entity(login).build();	
		}
		return Response.status(400).build();
	}
	
	@GET
	@Path("{userId}")
	@Produces("application/json")
	public Response getUserById(@PathParam("userId") int id){
		Connection c = DbConnection.Connect();
		Utente u = UtenteDao.getUtente(c, id);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(u.toJson()).build();		
	}	
	
	@PUT
	@Path("{userId}")
	public Response editUtente(@PathParam("userId") int id, Utente newUser) {
		Connection c = DbConnection.Connect();
		UtenteDao.updateUtente(c, id, newUser);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
