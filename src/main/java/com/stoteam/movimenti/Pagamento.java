/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.movimenti;

import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.carte.Bancomat;
import com.stoteam.conto.Conto;

public class Pagamento extends Movimento{

	private Conto destinatario;
	private Bancomat carta;
	
	public Pagamento(Conto mittente, double importo, String tipoMovimento, Conto destinatario, Bancomat carta) {
		super(mittente, importo, tipoMovimento);
		setDestinatario(destinatario);
		setCarta(carta);
	}
	
	public Pagamento(@JsonbProperty("mittenteIban") String mittenteIban, 
					@JsonbProperty("importo") double importo, 
					@JsonbProperty("tipoMovimento") String tipoMovimento,
					@JsonbProperty("isEseguito") boolean isEseguito,
					@JsonbProperty("destinatarioIban") String destinatarioIban, 
					@JsonbProperty("idCarta") int idCarta) {
		super(mittenteIban, importo, tipoMovimento, isEseguito);
		setDestinatario(destinatario);
		setCarta(carta);
	}
	/**
	 * @param getDestinatario - Ottiene Destinatario
	 * @return Destinatario
	 */
	public Conto getDestinatario() {
		return destinatario;
	}
	/**
	 * @param setDestinatario - Imposta Destinatario
	 * @return Destinatario
	 */
	public void setDestinatario(Conto destinatario) {
		if(destinatario != null)
			this.destinatario = destinatario;
	}
	/**
	 * @param getCarta - Ottiene Carta
	 * @return Carta
	 */
	public Bancomat getCarta() {
		return carta;
	}
	/**
	 * @param setCarta - Imposta Carta
	 * @return Carta
	 */
	public void setCarta(Bancomat carta) {
		if(carta != null)
			this.carta = carta;
	}	
}
