package com.stoteam.carte;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.conto.Conto;
import com.stoteam.dao.ContoDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;
import java.sql.Timestamp;

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
		this.dataRilascio = LocalDateTime.now();
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

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContoId() {
		return this.contoId;
	}
	public void setContoId(int contoId) {
		this.contoId = contoId;
	}
	public boolean isBlock() {
		return isBlock;
	}
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}
	public Conto getConto() {
		return conto;
	}
	private void setConto(String codiceConto) {
		if(codiceConto != null && codiceConto.trim().length() > 0) {
			Connection c = DbConnection.Connect();
			this.conto = ContoDao.getConto(c, ContoDao.getIdConto(c, codiceConto));
			System.out.println("Conto carta settato");
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	public double getSpesaMensile() {
		return spesaMensile;
	}
	public void setSpesaMensile(double spesa) {
		this.spesaMensile = spesa;
	}
	public void addSpesaMensile(double spesa) {
		this.spesaMensile += spesa;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		if(pin != null && !pin.trim().isEmpty())
			this.pin = pin;
	}
	public String getCodSicurezza() {
		return codSicurezza;
	}
	public void setCodSicurezza(String codSicurezza) {
		if(codSicurezza != null && !codSicurezza.trim().isEmpty())
			this.codSicurezza = codSicurezza;
	}
	public Timestamp getDataScadenza() {
		return Timestamp.valueOf(dataScadenza);
	}
	public void setDataScadenza(Timestamp data) {
		this.dataScadenza = data.toLocalDateTime();
	}
	public Timestamp getDataRilascio() {
		return Timestamp.valueOf(dataRilascio);
	}
	public void setDataRilascio(Timestamp data) {
		this.dataRilascio = data.toLocalDateTime();
	}
}
