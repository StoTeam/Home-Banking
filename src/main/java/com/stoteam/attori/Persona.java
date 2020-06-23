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

public abstract class Persona {
	
	private int id;
	private String nome;
	private String cognome;	
	private String telefono;
	private String email;
	private String password;
	private String indirizzo;
	private boolean auth;
	private int tipoUtente;
	private int idIntestatario;
	
	public Persona(String nome, String cognome, String telefono, String email, String password, int tipoUtente, String indirizzo) {
		setNome(nome);
		setCognome(cognome);
		setTelefono(telefono);
		setEmail(email);
		setPassword(password);
		setTipoUtente(tipoUtente);
		setIndirizzo(indirizzo);
	}
	
	/**
 * @param getIdIntestatario - Ottiene l'ID intestatario
 * @return ID intestatario
 */
	
	public int getIdIntestatario() {
		return idIntestatario;
	}
	
/**
 * @param setIdIntestatario - Imposta l'ID intestatario
 * @return ID intestatario
 */
	
	public void setIdIntestatario(int id_intestatario) {
		this.idIntestatario = id_intestatario;
	}
	
	/**
	 * @param setId - Imposta l'ID
	 * @return ID
	 */
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param getId - Ottiene l'ID
	 * @return ID
	 */
	public int getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome != null && !nome.trim().isEmpty()) {
			this.nome = nome;
		}
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		if(cognome != null && !cognome.trim().isEmpty())
			this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		boolean reg = telefono.matches("^\\d+");
		if(telefono.trim().length() >= 6 && reg) {
			telefono = telefono.trim();
			this.telefono = telefono;
		}
		else throw new IllegalArgumentException();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email != null) {
			email = email.trim();
			boolean reg = email.matches("^\\w+@[a-zA-Z_.]+?\\.[a-zA-Z]{2,3}$");
			if(reg) {
			this.email = email;
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != null && password.trim().length() >= 8)
			this.password = password;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public int getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(int tipoUtente) {
		if(tipoUtente >= 0 && tipoUtente < 2)
		this.tipoUtente = tipoUtente;
	}
	public String getIndirizzo() {
		return this.indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		if(indirizzo != null && !indirizzo.trim().isEmpty())
			this.indirizzo = indirizzo;
	}
}
