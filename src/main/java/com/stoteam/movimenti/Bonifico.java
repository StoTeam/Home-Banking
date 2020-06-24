/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.movimenti;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;

public class Bonifico extends Movimento{

	private Conto destinatario;
	private String causale;
	private LocalDateTime dataArrivo;

	public Bonifico(double importo, Conto mittente, String tipoMovimento, Conto destinatario, String causale) {
		super(mittente, importo, tipoMovimento);
		setDestinatario(destinatario);
		setCausale(causale);
		dataArrivo = LocalDateTime.now().plusMinutes(10);
	}
	@JsonbCreator
	public Bonifico(
			@JsonbProperty("importo") double importo, 
			@JsonbProperty("contoIban") String contoIban, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito,
			@JsonbProperty("ibanContoD") String ibanContoD, 
			@JsonbProperty("causale") String causale) {
		super(contoIban, importo, tipoMovimento, isEseguito);
		setDestinatario(ibanContoD);
		setCausale(causale);
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
	 * @param setDestinatario - Imposta IBAN Destinatario
	 * @return Destinatario
	 */
	public void setDestinatario(String ibanContoD) {
		Connection c = DbConnection.Connect();
		ContoDao.getConto(c, ContoDao.getIdContoByIban(c, ibanContoD));
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param getCausale - Ottiene Causale
	 * @return Causale
	 */
	public String getCausale() {
		return causale;
	}
	/**
	 * @param setCausale - Imposta Causale
	 * @return Causale
	 */
	public void setCausale(String causale) {
		if(causale != null && !causale.trim().isEmpty())
			this.causale = causale;
	}
	/**
	 * @param getDataArrivo - Ottiene Data Arrivo
	 * @return Data Arrivo
	 */
	public Timestamp getDataArrivo() {
		Timestamp ts = Timestamp.valueOf(dataArrivo);
		return ts;
	}
	/**
	 * @param setDataArrivo - Imposta Data Arrivo
	 * @return Data Arrivo
	 */
	public void setDataArrivo(String data) {
		Timestamp ts = Timestamp.valueOf(data);
		dataArrivo = ts.toLocalDateTime();
	}
}
