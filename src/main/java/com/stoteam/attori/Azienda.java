/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
@param nome - Nome Cliente
@param cognome - Cognome Cliente
@param telefono - Recapito Telefonico Cliente
@param email - Indirizzo Email Cliente
@param password - Password accesso Cliente
@param tipoUtente - Tipologia Utente
@param indirizzo - Indirizzo Cliente
@param ragioneSociale - Ragione Sociale Azienda
@param pIva - Partita IVA Azienda
*/
package com.stoteam.attori;

public class Azienda extends Persona {
	
	private String ragioneSociale;
	private String pIva;

	public Azienda(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo, String ragioneSociale, String pIva) {
		super(nome, cognome, telefono, email, password, tipoUtente, indirizzo);
		setRagioneSociale(ragioneSociale);
		setpIva(pIva);
		
	}	
	/**
	 * @param getRagioneSociale - Ottiene una stringa contenente la ragione sociale
	 * @return Ragione Sociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}	
	/**
	 * @param setRagioneSociale - Imposta una stringa contenente la ragione sociale
	 * @return Ragione Sociale
	 */	
	public void setRagioneSociale(String ragioneSociale) {
		if(ragioneSociale != null && !ragioneSociale.trim().isEmpty())
			this.ragioneSociale = ragioneSociale;
	}	
	/**
	 * @param getpIva - Ottiene una stringa contenente la partita IVA
	 * @return Partita IVA
	 */	
	public String getpIva() {
		return pIva;
	}	
	/**
	 * @param getpIva - Imposta una stringa contenente la partita IVA
	 * @return Partita IVA
	 */	
	public void setpIva(String pIva) {		
		boolean reg = pIva.matches("p{Digit}{11}");
		if(reg) {
			this.pIva = pIva;
		}
	}
	@Override
	public String toString() {
		return "Azienda [ragioneSociale=" + ragioneSociale + ", pIva=" + pIva + ", getIdIntestatario()="
				+ getIdIntestatario() + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getCognome()="
				+ getCognome() + ", getTelefono()=" + getTelefono() + ", getEmail()=" + getEmail() + ", isAuth()=" + isAuth()
				+ ", getTipoUtente()=" + getTipoUtente() + ", getIndirizzo()=" + getIndirizzo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}