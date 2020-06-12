package com.stoteam.carte;

import com.stoteam.conto.Conto;

public class CCredito extends Bancomat{

	double limite;
	double disponibilità;
	boolean usoPin = false;
	public CCredito(Conto utente, String pin, String codSicurezza) {
		super(utente, pin, codSicurezza);
		setLimite(limite);
		this.disponibilità = limite;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		if(limite > 0)
			this.limite = limite;
	}
	public double getDisponibilità() {
		return disponibilità;
	}
	public boolean isUsoPin() {
		return usoPin;
	}
	public void setUsoPin(boolean usoPin) {
		this.usoPin = usoPin;
	}	
}
