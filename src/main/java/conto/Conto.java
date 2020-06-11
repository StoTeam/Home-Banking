package conto;

import attori.Persona;

public class Conto {

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
		boolean reg = iban.matches("^(IT)[0-9]{2}[A-Z][0-9]{10}[0-9A-Z]{12}$");
		if(reg)
			this.iban = iban;
	}
	public Persona getUtente() {
		return utente;
	}
	public void setUtente(Persona utente) {
		if(utente != null)
			this.utente = utente;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
