package com.stoteam.carte;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.stoteam.conto.Conto;

public class Bancomat {

	private int id = 0;
	private int contoId;
	private boolean isBlock;
	private Conto utente;
	private double spesaMensile;
	private String pin;
	private LocalDate dataRilascio;
	private LocalDate dataScadenza;
	private String codSicurezza;
	
	public Bancomat(Conto utente, String pin, String codSicurezza) {
		this.utente = utente;
		this.pin = pin;
		this.dataRilascio = LocalDate.now();
		this.dataScadenza = dataRilascio.plusYears(3);
		this.codSicurezza = codSicurezza;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContoId() {
		return this.contoId;
	}
	public void setContoId(int contoId) {
		this.contoId = contoId;
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
	public void setSpesaMensile(double spesa) {
		this.spesaMensile = spesa;
	}
	public void addSpesaMensile(double spesa) {
		this.spesaMensile += spesa;
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
	public void setDataScadenza(String data) {
		this.dataScadenza = LocalDate.parse(data);
	}
	public LocalDate getDataRilascio() {
		return dataRilascio;
	}
	public void setDataRilascio(String data) {
		this.dataRilascio = LocalDate.parse(data);
	}
}
