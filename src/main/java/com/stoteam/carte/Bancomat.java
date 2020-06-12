package com.stoteam.carte;

import java.time.LocalDate;

import com.stoteam.conto.Conto;

public class Bancomat {

	private boolean isBlock;
	private Conto utente;
	private double spesaMensile;
	private String pin;
	private final LocalDate dataRilascio;
	private final LocalDate dataScadenza;
	private String codSicurezza;
	
	public Bancomat(Conto utente, String pin, String codSicurezza) {
		this.utente = utente;
		this.pin = pin;
		this.dataRilascio = LocalDate.now();
		this.dataScadenza = dataRilascio.plusYears(3);
		this.codSicurezza = codSicurezza;
	}

	public boolean isBlock() {
		return isBlock;
	}
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}
	public Conto getUtente() {
		return utente;
	}
	private void setUtente(Conto utente) {
		if(utente != null)
			this.utente = utente;
	}
	public double getSpesaMensile() {
		return spesaMensile;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		if(pin != null && !pin.trim().isEmpty())
			this.pin = pin;
	}
	public String getCodSicurezza() {
		return codSicurezza;
	}
	public void setCodSicurezza(String codSicurezza) {
		if(codSicurezza != null && !codSicurezza.trim().isEmpty())
			this.codSicurezza = codSicurezza;
	}
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
}
