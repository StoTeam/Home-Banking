package com.stoteam.carte;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.stoteam.conto.Conto;

public class Bancomat {

	private int id;
	private int contoId;
	private boolean isBlock;
	private Conto utente;
	private double spesaMensile;
	private String pin;
	private LocalDateTime dataRilascio;
	private LocalDateTime dataScadenza;
	private String codSicurezza;
	
	public Bancomat(Conto utente, String pin, String codSicurezza) {
		this.utente = utente;
		this.pin = pin;
		this.dataRilascio = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
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
	public Timestamp getDataScadenza() {
		return Timestamp.valueOf(this.dataScadenza);
	}
	public void setDataScadenza(String data) {
		this.dataScadenza = Timestamp.valueOf(data).toLocalDateTime();
	}
	public Timestamp getDataRilascio() {
		return Timestamp.valueOf(this.dataRilascio);
	}
	public void setDataRilascio(String data) {
		this.dataRilascio = Timestamp.valueOf(data).toLocalDateTime();
	}
}
