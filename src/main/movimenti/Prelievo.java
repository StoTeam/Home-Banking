package movimenti;

import conto.Conto;

public class Prelievo extends Movimento{

	
	public Prelievo(Conto utente, double importo) {
		super(utente, importo);
	}

}
