package com.stoteam.movimenti;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.stoteam.attori.Persona;
import com.stoteam.conto.Conto;

public class Bonifico extends Movimento{

	private Conto destinatario;
	private String causale;
	private LocalDateTime dataArrivo;
	
	public Bonifico(double importo, Conto mittente, String tipoMovimento, Conto destinatario, String causale) {
		super(mittente, importo, tipoMovimento);
		setDestinatario(destinatario);
		setCausale(causale);
	}
	public Conto getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Conto destinatario) {
		if(destinatario != null)
			this.destinatario = destinatario;
	}
	public String getCausale() {
		return causale;
	}
	public void setCausale(String causale) {
		if(causale != null && !causale.trim().isEmpty())
			this.causale = causale;
	}
	public String getDataArrivo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dataArrivo);
	}
	public void setDataArrivo(String data) {
		dataArrivo = LocalDateTime.parse(data);
	}
}
