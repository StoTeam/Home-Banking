/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.movimenti;

import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;

public class Prelievo extends Movimento{

	public Prelievo(Conto utente, double importo, String tipoMovimento) {
		super(utente, importo, tipoMovimento);
	}

	public Prelievo(
			@JsonbProperty("importo") double importo,
			@JsonbProperty("ibanUtente") String ibanUtente, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito) {
		super(ibanUtente, importo, tipoMovimento, isEseguito);
	}
}
