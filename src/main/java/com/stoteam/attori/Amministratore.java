package com.stoteam.attori;

import java.util.ArrayList;
import java.util.List;

public class Amministratore extends Persona {

	
	//cambiare con ENUM
	private String livelloAccesso;
	private List<String> areaCompetenza = new ArrayList<>();
	
	
	public Amministratore(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
	}

}
