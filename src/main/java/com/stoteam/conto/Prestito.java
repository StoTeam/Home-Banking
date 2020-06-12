package com.stoteam.conto;

import java.time.LocalDateTime;

import com.stoteam.attori.Persona;

public class Prestito {
	
	private double importo;
	private double dovuti;
	private double pagati = 0;
	private double tan;
	private double taeg;
	private int tempo; //per quanto tempo dura il prestito
	private boolean isFisso;
	private boolean isApprovato;
	private final LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private LocalDateTime dataRata;
	private Conto utente;
	
	public Prestito(double importo, double tan, double taeg, int tempo, boolean isFisso, Conto utente) {
		setImporto(importo);
		setTan(tan);
		setTaeg(taeg);
		setTempo(tempo);
		setFisso(isFisso);
		setUtente(utente);
		this.dataInizio = LocalDateTime.now();
		this.dataFine = dataInizio.plusYears(tempo);
		this.dataRata = dataInizio.plusMonths(1);
	}

	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		if(importo > 0)
			this.importo = importo;
	}
	public double getDovuti() {
		return dovuti;
	}
	public double getPagati() {
		return pagati;
	}
	public void setPagati(double pagati) {
		if(pagati > 0)
			this.pagati += pagati;
	}
	public double getTan() {
		return tan;
	}
	public void setTan(double tan) {
		if(tan > 0)
			this.tan = tan;
	}
	public double getTaeg() {
		return taeg;
	}
	public void setTaeg(double taeg) {
		if(taeg > 0)
			this.taeg = taeg;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		if(tempo > 0)
			this.tempo = tempo;
	}
	public boolean isFisso() {
		return isFisso;
	}
	public void setFisso(boolean isFisso) {
		this.isFisso = isFisso;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public LocalDateTime getDataRata() {
		return dataRata;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public Conto getUtente() {
		return utente;
	}
	public void setUtente(Conto utente) {
		if(utente != null)
			this.utente = utente;
	}	
}
