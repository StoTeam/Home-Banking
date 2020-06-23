package com.stoteam.conto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.stoteam.attori.Persona;

public class Prestito {
	
	private int id;
	private double importo;
	private double dovuti;
	private double pagati = 0;
	private double tan;
	private double taeg;
	private int tempo; //per quanto tempo dura il prestito
	private boolean isFisso;
	private boolean isApprovato;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private LocalDateTime dataRata;
	private Conto conto;
	
	public Prestito(double importo, double tan,
			double taeg, int tempo, boolean isFisso,
			Conto conto) {
		setImporto(importo);
		setTan(tan);
		setTaeg(taeg);
		setTempo(tempo);
		setFisso(isFisso);
		setConto(conto);
		this.dataInizio = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		this.dataFine = dataInizio.plusYears(tempo);
		this.dataRata = dataInizio.plusMonths(1);
	}

	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		if(importo > 0)
			this.importo = importo;
	}
	public double getDovuti() {
		return dovuti;
	}
	public void setDovuti(double dovuti) {
		this.dovuti = dovuti;
	}
	public double getPagati() {
		return pagati;
	}
	public void addPagati(double pagati) {
		if(pagati > 0)
			this.pagati += pagati;
	}
	public void setPagati(double pagati) {
		this.pagati = pagati;
	}
	public double getTan() {
		return tan;
	}
	public void setTan(double tan) {
		if(tan > 0)
			this.tan = tan;
	}
	public double getTaeg() {
		return taeg;
	}
	public void setTaeg(double taeg) {
		if(taeg > 0)
			this.taeg = taeg;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		if(tempo > 0)
			this.tempo = tempo;
	}
	public boolean isFisso() {
		return isFisso;
	}
	public void setFisso(boolean isFisso) {
		this.isFisso = isFisso;
	}
	public Timestamp getDataFine() {
		return Timestamp.valueOf(this.dataFine);
	}
	public void setDataFine(Timestamp data) {
		this.dataFine = data.toLocalDateTime();
	}
	public Timestamp getDataRata() {
		return Timestamp.valueOf(this.dataRata);
	}
	public void setDataRata(Timestamp data) {
		this.dataRata = data.toLocalDateTime();
	}
	public Timestamp getDataInizio() {
		return Timestamp.valueOf(this.dataInizio);
	}
	public void setDataInizio(Timestamp data) {
		this.dataInizio = data.toLocalDateTime();
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		if(conto != null)
			this.conto = conto;
	}

	public boolean getIsFisso() {
		return isFisso;
	}

	public boolean getIsApprovato() {
		return isApprovato;
	}
	public void setIsApprovato(boolean Approvato) {
		this.isApprovato = Approvato;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}	
}
