package com.stoteam.movimenti;

import java.time.LocalDateTime;

import com.stoteam.conto.Conto;

public abstract class Movimento {

	Conto utente;
	double importo;
	private final LocalDateTime dataEsecuzione;
	
	public Movimento(Conto conto, double importo) {
		this.dataEsecuzione = LocalDateTime.now();
		setUtente(conto);
		setImporto(importo);
		
	}	
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		if(importo > 0);
			this.importo = importo;
	}
	
	public Conto getUtente() {
		return utente;
	}
	public void setUtente(Conto utente) {
		if(utente != null)
			this.utente = utente;
	}
	public LocalDateTime getDataEsecuzione() {
		return dataEsecuzione;
	}
}
