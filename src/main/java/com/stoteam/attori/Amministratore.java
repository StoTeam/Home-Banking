/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.attori;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stoteam.dao.AmministratoreDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

public class Amministratore extends Persona {

	private String livelloAccesso;
	private List<String> areaCompetenza = new ArrayList<>();
	
	
	public Amministratore(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo, String livelloAccesso, List<String> areaCompetenza) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
		setLivelloAccesso(livelloAccesso);
		setAreaCompetenza(areaCompetenza);
	
	}
	
	/**
	 * @param getLivelloAccesso - Ottiene il livello di accesso
	 * @return Livello di Accesso in formato String
	 */
	
	public String getLivelloAccesso() {
		return livelloAccesso;
	}

	/**
	* @param toCSV - Trasforma la lista delle aree di competenza nel formato CSV
	* @return Aree di competenza
	*/
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		if(areaCompetenza.size() > 1) {
			sb.append(areaCompetenza.get(0));
		} else {
			for(int i = 0; i < areaCompetenza.size()-1; i++) {
				sb.append(areaCompetenza.get(i) + ";");
			}
			sb.append(areaCompetenza.get(areaCompetenza.size()-1));			
		}
		return sb.toString();
	}
	
	public List<String> CSVToList(String string) {
		List<String> l  = new ArrayList<>(Arrays.asList(string.split(";")));
		return l;
	}
	
	/**
	* @param setAreaCompetenza - Imposta area di competenza utente
	* @return Area di competenza
	*/
	
	public void setAreaCompetenza(List<String> areaCompetenza) {
		this.areaCompetenza = areaCompetenza;
		
	}

	/**
	* @param setLivelloAccesso - Imposta Livello Accesso Utente
	* @return Livello Accesso
	*/
	
	public void setLivelloAccesso(String livelloAccesso) {
		this.livelloAccesso = livelloAccesso;
		
	}
	
	@Override
	public String toString() {
		return "Amministratore [livelloAccesso=" + livelloAccesso + ", areaCompetenza=" + toCSV() + "]";
	}
	
}
