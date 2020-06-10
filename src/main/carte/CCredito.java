package carte;

import conto.Conto;

public class CCredito extends Bancomat{

	double limite;
	double disponibilitā;
	boolean usoPin = false;
	public CCredito(Conto utente, String pin, String codSicurezza) {
		super(utente, pin, codSicurezza);
		setLimite(limite);
		this.disponibilitā = limite;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		if(limite > 0)
			this.limite = limite;
	}
	public double getDisponibilitā() {
		return disponibilitā;
	}
	public boolean isUsoPin() {
		return usoPin;
	}
	public void setUsoPin(boolean usoPin) {
		this.usoPin = usoPin;
	}	
}
