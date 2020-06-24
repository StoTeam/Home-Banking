/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.carte;

import javax.json.bind.annotation.JsonbProperty;

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
	public CCredito(
			@JsonbProperty("codiceConto") String codiceConto,
			@JsonbProperty("pin") String pin, 
			@JsonbProperty("codSicurezza") String codSicurezza,
			@JsonbProperty("limite") double limite) {
		super(codiceConto, pin, codSicurezza);
		setLimite(limite);
		setConto(codiceConto);
		this.disponibilita = limite;
	}
	
	/**
	 * @param getLimite - Ottiene limite carta di credito
	 * @return Limite carta di credito
	 */
	
	public double getLimite() {
		return limite;
	}
	
	/**
	 * @param setLimite - Imposta limite carta di credito
	 * @return Limite carta di credito
	 */
	
	public void setLimite(double limite) {
		if(limite >= 0)
			this.limite = limite;
	}

	/**
	 * @param getDisponibilita - Ottieni disponibilità carta di credito
	 * @return Disponibilità carta di credito
	 */
	
	public double getDisponibilita() {
		return disponibilita;
	}

	/**
	 * @param setDisponibilita - Imposta disponibilità carta di credito
	 * @return Disponibilità carta di credito
	 */
	
	public void setDisponibilita(double disponibilita) {
		if(disponibilita >= 0)
		this.disponibilita = disponibilita;
	}

	/**
	 * @param isUsoPin - Verifica se è utilizzato il PIN
	 * @return Utilizzo PIN carta di credito
	 */
	
	public boolean isUsoPin() {
		return usoPin;
	}

	/**
	 * @param setUsoPin - Imposta utilizzo il PIN
	 * @return Utilizzo PIN carta di credito
	 */
	
	public void setUsoPin(boolean usoPin) {
		this.usoPin = usoPin;
	}
}
