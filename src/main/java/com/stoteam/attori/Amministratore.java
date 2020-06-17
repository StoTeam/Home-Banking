package com.stoteam.attori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Amministratore extends Persona {

	
	//cambiare con ENUM
	private String livelloAccesso;
	private List<String> areaCompetenza = new ArrayList<>();
	
	
	public Amministratore(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
		setLivelloAccesso(livelloAccesso);
		setAreaCompetenza(areaCompetenza);
	
	}

	public String getLivelloAccesso() {
		return livelloAccesso;
	}


	public String competenzaCSV() {
		StringBuilder sb = new StringBuilder();
		for(String s : areaCompetenza) {
			sb.append(s + ";");
		}
		return sb.toString();
	}
	
	public List<String> CSVToList(String string) {
		List<String> l  = new ArrayList<>(Arrays.asList(string.split(";")));
		return l;
	}
	

	public void setAreaCompetenza(List<String> areaCompetenza) {
		this.areaCompetenza = areaCompetenza;
		
	}


	private void setLivelloAccesso(String livelloAccesso) {
		this.livelloAccesso = livelloAccesso;
		
	}


	public void setId(int int1) {
		
	}
	
	
	
	public String toCSV(String string) {
		return toCSV(";");
	}

	
	@Override
	public String toString() {
		return "Amministratore [livelloAccesso=" + livelloAccesso + ", areaCompetenza=" + areaCompetenza + "]";
	}
	
	


	
	

}
