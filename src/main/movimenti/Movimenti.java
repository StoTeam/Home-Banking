package movimenti;

import java.util.ArrayList;

import conto.Conto;

public class Movimenti<T> extends ArrayList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6297837152171278512L;

	Conto utente;
	
	public Movimenti(Conto utente) {
		setUtente(utente);
	}
	public Conto getUtente() {
		return utente;
	}
	private void setUtente(Conto utente) {
		if(utente != null)
			this.utente = utente;
	}
	
}
