package com.stoteam.movimenti;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbProperty;

import com.stoteam.attori.Persona;
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
	public Bonifico(
			@JsonbProperty("importo") double importo, 
			@JsonbProperty("ibanContoM") String ibanContoM, 
			@JsonbProperty("tipoMovimento") String tipoMovimento,
			@JsonbProperty("isEseguito") boolean isEseguito,
			@JsonbProperty("ibanContoD") String ibanContoD, 
			@JsonbProperty("causale") String causale) {
		super(ibanContoM, importo, tipoMovimento, isEseguito);
		setDestinatario(ibanContoD);
		setCausale(causale);
	}
	public Conto getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Conto destinatario) {
		if(destinatario != null)
			this.destinatario = destinatario;
	}
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
	public String getCausale() {
		return causale;
	}
	public void setCausale(String causale) {
		if(causale != null && !causale.trim().isEmpty())
			this.causale = causale;
	}
	public Timestamp getDataArrivo() {
		Timestamp ts = Timestamp.valueOf(dataArrivo);
		return ts;
	}
	public void setDataArrivo(String data) {
		Timestamp ts = Timestamp.valueOf(data);
		dataArrivo = ts.toLocalDateTime();
	}
}
