/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
 */

package com.stoteam.carte;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;
import com.stoteam.dao.CartaDao;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;

public class Bancomat {

	private int id = 0;
	private int contoId;
	private boolean isBlock;
	private Conto conto;
	private double spesaMensile;
	private String pin;
	private LocalDateTime dataRilascio;
	private LocalDateTime dataScadenza;
	private String codSicurezza;


	public Bancomat(Conto conto, String pin, String codSicurezza) {
		this.conto = conto;
		this.pin = pin;
		this.dataRilascio = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		this.dataScadenza = dataRilascio.plusYears(3);
		this.codSicurezza = codSicurezza;
	}
	@JsonbCreator
	public Bancomat(@JsonbProperty("codiceConto") String conto, 
			@JsonbProperty("pin") String pin, 
			@JsonbProperty("codSicurezza") String codSicurezza) {
		setConto(conto);
		this.pin = pin;
		this.codSicurezza = codSicurezza;
		this.dataRilascio = LocalDateTime.now();
		this.dataScadenza = dataRilascio.plusYears(3);
	}

	public Bancomat() {
	}
	/**
	 * @param getId - Ottiene l'ID
	 * @return ID
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param setId - Imposta l'ID
	 * @return ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param getId - Ottiene l'ID del Conto
	 * @return ID Conto
	 */
	public int getContoId() {
		return this.contoId;
	}
	/**
	 * @param setId - Imposta l'ID del Conto
	 * @return ID Conto
	 */
	public void setContoId(int contoId) {
		if(contoId>0)
			this.contoId = contoId;
	}
	/**
	 * @param isBlock - Verifica che la carta sia bloccata
	 * @return Blocco Carta
	 */
	public boolean isBlock() {
		return isBlock;
	}
	/**
	 * @param setBlock - Imposta il blocco sulla carta
	 * @return Blocco Carta
	 */
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}
	/**
	 * @param getConto - Ottiene l'oggetto Conto
	 * @return Conto
	 */
	public Conto getConto() {
		return conto;
	}
	/**
	 * @param setConto - Imposta Conto Carta
	 * @return Conto
	 */
	protected void setConto(String idConto) {
		if(idConto != null && idConto.trim().length() > 0) {
			Connection c = DbConnection.Connect();
			this.conto = ContoDao.getConto(c, ContoDao.getIdConto(c, idConto));
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	/**
	 * @param getSpesaMensile - Ottiene importo spesa mensile
	 * @return Spesa Mensile
	 */
	public double getSpesaMensile() {
		return spesaMensile;
	}

	/**
	 * @param setSpesaMensile - Imposta spesa mensile
	 * @return Spesa Mensile
	 */
	public void setSpesaMensile(double spesa) {
		if(spesa >= 0)
			this.spesaMensile = spesa;
	}
	/**
	 * @param addSpesaMensile - Aggiunge importo spesa mensile
	 * @return Spesa Mensile
	 */
	public void addSpesaMensile(double spesa) {
		this.spesaMensile += spesa;
	}
	/**
	 * @param getPin - Ottiene PIN carta
	 * @return PIN Carta
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param setPin - Imposta PIN carta
	 * @return PIN Carta
	 */
	public void setPin(String pin) {
		if(pin != null && !pin.trim().isEmpty())
			this.pin = pin;
	}
	/**
	 * @param getCodSicurezza - Ottiene codice di sicurezza
	 * @return Codice Sicurezza
	 */
	public String getCodSicurezza() {
		return codSicurezza;
	}
	/**
	 * @param setCodSicurezza - Imposta codice di sicurezza
	 * @return Codice Sicurezza
	 */
	public void setCodSicurezza(String codSicurezza) {
		if(codSicurezza != null && !codSicurezza.trim().isEmpty())
			this.codSicurezza = codSicurezza;
	}
	/**
	 * @param getDataScadenza - Ottiene data scadenza carta
	 * @return Scadenza Carta
	 */
	public Timestamp getDataScadenza() {
		return Timestamp.valueOf(dataScadenza);
	}
	/**
	 * @param setDataScadenza - Imposta data scadenza carta
	 * @return Scadenza Carta
	 */
	public void setDataScadenza(Timestamp data) {
		this.dataScadenza = data.toLocalDateTime();
	}
	/**
	 * @param getDataRilascio - Ottiene data rilascio carta
	 * @return Rilascio Carta
	 */
	public Timestamp getDataRilascio() {
		return Timestamp.valueOf(dataRilascio);
	}
	/**
	 * @param setDataRilascio - Imposta data rilascio carta
	 * @return Rilascio Carta
	 */
	public void setDataRilascio(Timestamp data) {
		this.dataRilascio = data.toLocalDateTime();
	}
	/**
	 * Aggiunge una nuova carta sul db
	 */
	public void salva() {
		Connection c = DbConnection.Connect();
		CartaDao.UpCarta(c, this);
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Restituisce una carta dal DB
	 * @param idCarta
	 * @return
	 */
	public Bancomat download(int idCarta) {
		Connection c = DbConnection.Connect();
		Bancomat ca = CartaDao.getCarta(c, idCarta);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ca;
	}
	/**
	 * Aggiorna una carta nel DB
	 * @param idCarta
	 * @return
	 */
	public Bancomat update(int idCarta) {
		Connection c = DbConnection.Connect();
		CartaDao.updateCarta(c, idCarta, this);
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
}
