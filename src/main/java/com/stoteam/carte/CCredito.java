package com.stoteam.carte;

import com.stoteam.conto.Conto;

public class CCredito extends Bancomat{

	double limite;
	double disponibilita;
	boolean usoPin = false;
	public CCredito(Conto utente, String pin, String codSicurezza, double limite) {
		super(utente, pin, codSicurezza);
		setLimite(limite);
		this.disponibilita = limite;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		if(limite > 0)
			this.limite = limite;
	}
	public double getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(double disponibilita) {
		this.disponibilita = disponibilita;
	}
	public boolean isUsoPin() {
		return usoPin;
	}
	public void setUsoPin(boolean usoPin) {
		this.usoPin = usoPin;
	}
}
