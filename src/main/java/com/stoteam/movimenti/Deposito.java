package com.stoteam.movimenti;

import java.time.LocalDateTime;

import com.stoteam.attori.Persona;
import com.stoteam.conto.Conto;

public class Deposito extends Movimento{
	
	public Deposito(Conto utente, String tipoMovimento, double importo) {
		super(utente, importo, tipoMovimento);
	}
	
}
