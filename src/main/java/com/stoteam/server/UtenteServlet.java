package com.stoteam.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//TODO gestione connessioni.
@WebServlet(urlPatterns = {"/utente"})
public class UtenteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override //Create new User
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = req.getReader();
		while(br.ready())
			System.out.println(br.readLine());
	}	

}
