package com.stoteam.movimenti;

import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;

public class Deposito extends Movimento{
	
	public Deposito(Conto utente, String tipoMovimento, double importo) {
		super(utente, importo, tipoMovimento);
	}
	public Deposito(
			@JsonbProperty("importo") double importo,
			@JsonbProperty("ibanUtente") String ibanUtente, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito) {
		super(ibanUtente, importo, tipoMovimento, isEseguito);
	}	
	@Override
	public void esegui() {
		double cifra = conto.aggiungiDenaro(importo);
		this.setEseguito(true);
	}
}
