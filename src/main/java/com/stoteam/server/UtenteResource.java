package com.stoteam.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import com.stoteam.attori.*;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

@Path("/utente")
public class UtenteResource {
	
	@POST
	@Produces("application/json")
	public Response createUser(Utente utente) {
		Utente u = new Utente(utente.getNome(), utente.getCognome(), utente.getTelefono(), utente.getEmail(), utente.getPassword(), utente.getTipoUtente(), utente.getIndirizzo(), utente.getCodiceFiscale());
		Connection c = DbConnection.Connect();
		UtenteDao.UpUtente(c, u);
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(418).build();
		}
		return Response.status(200).entity(utente.toJson()).build();
	}
	
	@POST
	@Path("login")
	@Produces("application/json")
	public Response logIn(LoginData ld) {
		Connection c = DbConnection.Connect();
		int id = UtenteDao.checkLogUtente(c, ld.getEmail(), ld.getPassword());
		if(id > 0) {
			Cookie login = new Cookie("logged", "true");
			return Response.status(200).entity(UtenteDao.getUtente(c, id).toJson()).build();
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(404).build();
	}
}
