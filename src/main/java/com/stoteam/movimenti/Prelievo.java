package com.stoteam.movimenti;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;

public class Prelievo extends Movimento{

	public Prelievo(Conto utente, double importo, String tipoMovimento) {
		super(utente, importo, tipoMovimento);
	}
	@JsonbCreator
	public Prelievo(
			@JsonbProperty("importo") double importo,
			@JsonbProperty("contoIban") String contoIban, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito) {
		super(contoIban, importo, tipoMovimento, isEseguito);
	}
}
