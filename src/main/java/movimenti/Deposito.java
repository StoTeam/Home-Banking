package movimenti;

import java.time.LocalDateTime;

import attori.Persona;
import conto.Conto;

public class Deposito extends Movimento{
	
	public Deposito(Conto utente, double importo) {
		super(utente, importo);
	}
	
}
