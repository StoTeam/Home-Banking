package com.stoteam.movimenti;

import com.stoteam.carte.Bancomat;
import com.stoteam.conto.Conto;

public class Pagamento extends Movimento{

	private Conto destinatario;
	private Bancomat carta;
	
	public Pagamento(Conto conto, double importo, String tipoMovimento, Bancomat carta) {
		super(conto, importo, tipoMovimento);
		setDestinatario(conto);
		setCarta(carta);
	}

	public Conto getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Conto destinatario) {
		if(destinatario != null)
			this.destinatario = destinatario;
	}
	public Bancomat getCarta() {
		return carta;
	}
	public void setCarta(Bancomat carta) {
		if(carta != null)
			this.carta = carta;
	}

	
	
}
