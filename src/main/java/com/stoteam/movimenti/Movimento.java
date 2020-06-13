package com.stoteam.movimenti;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.stoteam.conto.Conto;

public abstract class Movimento {

	int id;
	Conto conto;
	double importo;
	private String tipoMovimento;
	private LocalDateTime dataEsecuzione;
	
	public Movimento(Conto conto, double importo, String tipoMovimento) {
		this.dataEsecuzione = LocalDateTime.now();
		setConto(conto);
		setImporto(importo);
		setTipoMovimento(tipoMovimento);
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		if(importo > 0);
			this.importo = importo;
	}
	
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		if(conto != null)
			this.conto = conto;
	}
	public String getTipoMovimento() {
		return this.tipoMovimento;
	}
	public void setTipoMovimento(String tipoMovimento) {
		if(tipoMovimento != null && !tipoMovimento.trim().isEmpty()) {
			this.tipoMovimento = tipoMovimento.trim();
		}
	}
	public String getDataEsecuzione() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dataEsecuzione);
	}
	public void setDataEsecuzione(String data) {
		dataEsecuzione = LocalDateTime.parse(data);
	}
}
