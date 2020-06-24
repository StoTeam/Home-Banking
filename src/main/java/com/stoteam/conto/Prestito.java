/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
 */

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
	/**
	 * @param getImporto - Ottiene Importo
	 * @return Importo
	 */
	public double getImporto() {
		return importo;
	}
	/**
	 * @param setImporto - Imposta Importo
	 * @return Importo
	 */
	public void setImporto(double importo) {
		if(importo >= 0)
			this.importo = importo;
	}
	/**
	 * @param getDovuti - Ottiene Importo Dovuto
	 * @return Importo Dovuto
	 */
	public double getDovuti() {
		return dovuti;
	}
	/**
	 * @param setDovuti - Imposta Importo Dovuto
	 * @return Importo Dovuto
	 */
	public void setDovuti(double dovuti) {
		if(dovuti >= 0)
		this.dovuti = dovuti;
	}
	/**
	 * @param getPagati - Ottiene Importo Pagato
	 * @return Importo Pagato
	 */
	public double getPagati() {
		return pagati;
	}
	/**
	 * @param addPagati - Aggiunge a Importo Pagato
	 * @return Importo Pagato
	 */
	public void addPagati(double pagati) {
		if(pagati >= 0)
			this.pagati += pagati;
	}
	/**
	 * @param setPagati - Imposta Importo Pagato
	 * @return Importo Pagato
	 */
	public void setPagati(double pagati) {
		this.pagati = pagati;
	}
	/**
	 * @param getTan - Ottiene TAN
	 * @return TAN
	 */
	public double getTan() {
		return tan;
	}
	/**
	 * @param setTan - Imposta TAN
	 * @return TAN
	 */
	public void setTan(double tan) {
		if(tan >= 0)
			this.tan = tan;
	}
	/**
	 * @param getTaeg - Ottiene TAEG
	 * @return TAEG
	 */
	public double getTaeg() {
		return taeg;
	}
	/**
	 * @param setTaeg - Imposta TAEG
	 * @return TAEG
	 */
	public void setTaeg(double taeg) {
		if(taeg >= 0)
			this.taeg = taeg;
	}
	/**
	 * @param getTempo - Ottiene Tempo Prestito
	 * @return Tempo Prestito
	 */
	public int getTempo() {
		return tempo;
	}
	/**
	 * @param setTempo - Imposta Tempo Prestito
	 * @return Tempo Prestito
	 */
	public void setTempo(int tempo) {
		if(tempo >= 0)
			this.tempo = tempo;
	}
	/**
	 * @param isFisso - Ottiene un booleano relativo al tasso: fisso o variabile
	 * @return Tipologia Tasso
	 */
	public boolean isFisso() {
		return isFisso;
	}
	/**
	 * @param setFisso - Imposta il tipo di tasso fisso o variabile
	 * @return Tipologia Tasso
	 */
	public void setFisso(boolean isFisso) {
		this.isFisso = isFisso;
	}
	/**
	 * @param getDataFine - Ottiene la data di fine prestito
	 * @return Data Fine Prestito
	 */
	public Timestamp getDataFine() {
		return Timestamp.valueOf(this.dataFine);
	}
	/**
	 * @param setDataFine - Imposta la data di fine prestito
	 * @return Data Fine Prestito
	 */
	public void setDataFine(Timestamp data) {
		this.dataFine = data.toLocalDateTime();
	}
	/**
	 * @param getDataRata - Ottiene la data della rata
	 * @return Data Rata Prestito
	 */
	public Timestamp getDataRata() {
		return Timestamp.valueOf(this.dataRata);
	}
	/**
	 * @param setDataRata - Imposta la data della rata
	 * @return Data Rata Prestito
	 */
	public void setDataRata(Timestamp data) {
		this.dataRata = data.toLocalDateTime();
	}
	/**
	 * @param getDataInizio - Ottiene la data di inizio prestito
	 * @return Data Inizio Prestito
	 */
	public Timestamp getDataInizio() {
		return Timestamp.valueOf(this.dataInizio);
	}
	/**
	 * @param setDataInizio - Imposta la data di inizio prestito
	 * @return Data Inizio Prestito
	 */
	public void setDataInizio(Timestamp data) {
		this.dataInizio = data.toLocalDateTime();
	}
	/**
	 * @param getConto - Ottiene l'oggetto Conto
	 * @return Conto
	 */
	public Conto getConto() {
		return conto;
	}
	/**
	 * @param getConto - Imposta l'oggetto Conto
	 * @return Conto
	 */
	public void setConto(Conto conto) {
		if(conto != null)
			this.conto = conto;
	}
	/**
	 * @param getIsFisso - Ottiene la tipologia di tasso
	 * @return Tipologia tasso
	 */
	public boolean getIsFisso() {
		return isFisso;
	}
	/**
	 * @param getIsApprovato - Ottiene un booleano relativo all'approvazione del prestito
	 * @return Approvazione Prestito
	 */
	public boolean getIsApprovato() {
		return isApprovato;
	}
	/**
	 * @param setIsApprovato - Imposta l'approvazione del prestito
	 * @return Approvazione Prestito
	 */
	public void setIsApprovato(boolean Approvato) {
		this.isApprovato = Approvato;
	}
	/**
	 * @param setId - Imposta l'ID
	 * @return ID Prestito
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param getId - Ottiene l'ID
	 * @return ID Prestito
	 */
	public int getId() {
		return id;
	}	
}
