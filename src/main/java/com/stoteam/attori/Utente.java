/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
 */

package com.stoteam.attori;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

public class Utente extends Persona {

	private String codiceFiscale;

	public Utente() {
	}
	
	@JsonbCreator
	public Utente(@JsonbProperty("nome") String nome, 
			@JsonbProperty("cognome") String cognome,
			@JsonbProperty("telefono") String telefono, 
			@JsonbProperty("email") String email, 
			@JsonbProperty("password") String password, 
			@JsonbProperty("tipoUtente") int tipoUtente, 
			@JsonbProperty("indirizzo") String indirizzo, 
			@JsonbProperty("codiceFiscale") String codiceFiscale) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
		setCodiceFiscale(codiceFiscale);
	}

	/**
	 * @param getCodiceFiscale - Ottiene il codice fiscale dell'utente
	 * @return Codice Fiscale
	 */

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param setCodiceFiscale - Imposta il codice fiscale dell'utente
	 * @return Codice Fiscale
	 */

	public void setCodiceFiscale(String codiceFiscale) {
		boolean reg = codiceFiscale.matches("\\p{Upper}{6}\\p{Digit}{2}\\p{Upper}\\p{Digit}{2}\\p{Upper}\\p{Digit}{3}\\p{Upper}");		
		if(reg) {
			this.codiceFiscale = codiceFiscale;
		} else {
			throw new IllegalArgumentException(codiceFiscale + " Non è un codice fiscale valido");
		}
	}

	@Override
	public String toString() {
		return "Utente [codiceFiscale=" + codiceFiscale + ", getNome()=" + getNome() + ", getCognome()=" + getCognome()
		+ ", getTelefono()=" + getTelefono() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
		+ "]";
	}

	public String toJson() {
		JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {

			@Override
			public boolean isVisible(Method arg0) {
				return false;
			}

			@Override
			public boolean isVisible(Field arg0) {
				return true;
			}
		});
		return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
	}
}
