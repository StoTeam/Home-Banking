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
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		iban = iban.toUpperCase();
		//boolean reg = iban.matches("^(IT)[0-9]{2}[A-Z][0-9]{10}[0-9A-Z]{12}$");
		//if(reg)
			this.iban = iban;
	}
	public Utente getUtente() {
		return (Utente) utente;
	}
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
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getSaldoContabile() {
		return this.saldoContabile;
	}
	public void setIdIntestatario(int idInt) {
		this.idIntestatario = idInt;
	}
	public void setId(int id) {
		this.id = id;
	}
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
