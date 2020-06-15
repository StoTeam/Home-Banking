package com.stoteam.server;

import java.sql.Connection;

import org.bson.Document;

public class ControlloIngressi {

	public Document controllo(Connection c, String istr) {
		Document comando = Document.parse(istr);
		String com = comando.getString("azione");
		switch(com) {
		case "login":
			return Login.checkPass(c, comando.getString("email"), comando.getString("pass"));
		case "new_bonifico":
			System.out.println("Eseguo bonifico");
			return null;
			
			
		default:
			System.out.println("Non trovato");
			return null;
		}
	}
	
}
