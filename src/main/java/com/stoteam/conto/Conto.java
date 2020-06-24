package com.stoteam.conto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

import com.stoteam.attori.Persona;
import com.stoteam.attori.Utente;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

public class Conto {

	private int id;
	private int idIntestatario;
	private String codice;
	private String iban;
	private Persona utente;
	private double saldo;
	private double saldoContabile;

	public Conto(String codice, String iban, Persona utente, double saldo) {
		setCodice(codice);
		setIban(iban);
		setUtente(utente);
		setSaldo(saldo);
		this.saldoContabile = this.saldo;
	}
	@JsonbCreator
	public Conto(@JsonbProperty("codice") String codice,
			@JsonbProperty("iban") String iban, 
			@JsonbProperty("idIntestatario") int idIntestatario, 
			@JsonbProperty("saldo") double saldo) {
		setCodice(codice);
		setIban(iban);
		setUtente(idIntestatario);
		setSaldo(saldo);
		this.saldoContabile = this.saldo;
	}
	
	/**
	 * @param getCodice - Ottiene Codice Conto
	 * @return Codice Conto
	 */
	
	public String getCodice() {
		return codice;
	}

	/**
	 * @param setCodice - Imposta Codice Conto
	 * @return Codice Conto
	 */
	
	public void setCodice(String codice) {
		if(codice != null && codice.trim().length() > 0)
			this.codice = codice;
	}

	/**
	 * @param getIban - Ottieni IBAN Conto
	 * @return IBAN Conto
	 */
	
	public String getIban() {
		return iban;
	}

	/**
	 * @param getIban - Imposta IBAN Conto
	 * @return IBAN Conto
	 */
	
	public void setIban(String iban) {
		iban = iban.toUpperCase();

		boolean reg = iban.matches("^(IT)[0-9]{2}[A-Z][0-9]{10}[0-9A-Z]{12}$");
		if(reg)
			this.iban = iban;
	}

	/**
	 * @param getUtente - Ottieni l'oggetto utente
	 * @return Utente
	 */
	
	public Utente getUtente() {
		return (Utente) utente;
	}

	/**
	 * @param setUtente - Imposta l'oggetto utente
	 * @return Utente
	 */
	
	public void setUtente(Persona utente) {
		if(utente != null)
			this.utente = utente;
	}
	
	public void setUtente(int idUtente) {
		if(idUtente > 0) {
			Connection c = DbConnection.Connect();
			this.utente = UtenteDao.getUtente(c, idUtente);
			System.out.println("Utente conto settato");
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param getSaldo - Ottiene Saldo Conto
	 * @return Saldo Conto
	 */
	
	public double getSaldo() {
		return saldo;
	}
	
	/**
	 * @param setSaldo - Imposta Saldo Conto
	 * @return Saldo Conto
	 */
	
	public void setSaldo(double saldo) {
		if(saldo >= 0)
			this.saldo = saldo;
	}

	/**
	 * @param getSaldoContabile - Ottieni Saldo Contabile
	 * @return Saldo Contabile
	 */
	
	public double getSaldoContabile() {
		return this.saldoContabile;
	}

	/**
	 * @param setIdIntestatario - Imposta ID Intestatario
	 * @return ID Intestatario
	 */
	
	public void setIdIntestatario(int idInt) {
		if (idInt > 0)
		this.idIntestatario = idInt;
	}

	/**
	 * @param setId - Imposta ID
	 * @return ID
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param getId - Ottieni ID
	 * @return ID
	 */
	
	public int getId() {
		return this.id;
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
	@Override
	public String toString() {
		return "Conto [id=" + id + ", idIntestatario=" + idIntestatario + ", codice=" + codice + ", iban=" + iban
				+ ", utente=" + utente + ", saldo=" + saldo + ", saldoContabile=" + saldoContabile + "]";
	}

}
