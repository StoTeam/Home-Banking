package com.stoteam.movimenti;

import com.stoteam.conto.Conto;

public class Prelievo extends Movimento{

	
	public Prelievo(Conto utente, double importo, String tipoMovimento) {
		super(utente, importo, tipoMovimento);
	}

}
