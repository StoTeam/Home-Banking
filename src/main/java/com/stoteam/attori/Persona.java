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

import java.sql.Connection;
import java.sql.SQLException;

import com.stoteam.dao.AmministratoreDao;
import com.stoteam.dao.AziendaDao;
import com.stoteam.dao.DbConnection;
import com.stoteam.dao.UtenteDao;

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
	
	public Persona() {
	}	
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
	/**
	 * @param getNome - Ottiene il nome del cliente
	 * @return Nome
	 */	
	public String getNome() {
		return nome;
	}		
	/**
	 * @param setNome - Imposta il nome del cliente
	 * @return Nome
	 */	
	public void setNome(String nome) {
		if(nome != null && !nome.trim().isEmpty()) {
			this.nome = nome;
		}
	}	
	/**
	 * @param getCognome - Ottieni il cognome del cliente
	 * @return Cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param setCognome - Imposta il cognome del cliente
	 * @return Cognome
	 */
	public void setCognome(String cognome) {
		if(cognome != null && !cognome.trim().isEmpty())
			this.cognome = cognome;
	}	
	/**
	 * @param getTelefono - Ottieni il numero telefonico del cliente
	 * @return Numero Telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param setTelefono - Imposta il numero telefonico del cliente
	 * @return Numero Telefono
	 */
	public void setTelefono(String telefono) {
		boolean reg = telefono.matches("^\\d+");
		if(telefono.trim().length() >= 6 && reg) {
			telefono = telefono.trim();
			this.telefono = telefono;
		}
		else throw new IllegalArgumentException();
	}
	/**
	 * @param getEmail - Ottieni l'indirizzo email del cliente
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param setEmail - Imposta l'indirizzo email del cliente
	 * @return Email
	 */
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
	/**
	 * @param getPassword - Ottiene la password
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param setPassword - Imposta la password
	 * @return Password
	 */

	public void setPassword(String password) {
		if(password != null && password.trim().length() >= 8)
			this.password = password;
	}
	/**
	 * @param isAuth - Verifica che l'utente sia abilitato
	 * @return Autorizzazione
	 */

	public boolean isAuth() {
		return auth;
	}	
	/**
	 * @param setAuth - Imposta autorizzazioni utente
	 * @return Autorizzazione
	 */	
	public void setAuth(boolean auth) {
		this.auth = auth;
	}	
	/**
	 * @param getTipoUtente - Ottiene la tipologia dell'utente
	 * @return Tipo Utente
	 */	
	public int getTipoUtente() {
		return tipoUtente;
	}
	/**
	 * @param setTipoUtente - Imposta la tipologia dell'utente
	 * @return Tipo Utente
	 */	
	public void setTipoUtente(int tipoUtente) {
		if(tipoUtente >= 0 && tipoUtente < 2)
		this.tipoUtente = tipoUtente;
	}
	/**
	 * @param getIndirizzo - Ottiene l'indirizzo dell'utente
	 * @return Indirizzo
	 */
	public String getIndirizzo() {
		return this.indirizzo;
	}
	/**
	 * @param setIndirizzo - Imposta l'indirizzo dell'utente
	 * @return Indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		if(indirizzo != null && !indirizzo.trim().isEmpty())
			this.indirizzo = indirizzo;
	}	
	public void salva() {
		Connection c = DbConnection.Connect();
		if(this instanceof Utente) {
			UtenteDao.UpUtente(c, ((Utente) this));
		}else if(this instanceof Amministratore) {
			AmministratoreDao.UpAmministratore(c, ((Amministratore) this));
		}else if(this instanceof Azienda) {
			AziendaDao.UpAzienda(c, ((Azienda) this));
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void rimuovi() {
		Connection c = DbConnection.Connect();
		if(this instanceof Utente) {
			UtenteDao.removeUtente(c, this.getId());
		}else if(this instanceof Amministratore) {
			AmministratoreDao.removeAmministratore(c, this.getId());
		}else if(this instanceof Azienda) {
			AziendaDao.removeAzienda(c, this.getId());
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Persona downloadPersona(int id) {
		Connection c = DbConnection.Connect();
		Persona u = null;
		if(this instanceof Utente)
			u = UtenteDao.getUtente(c, id);
		else if(this instanceof Azienda)
			u = AziendaDao.getAzienda(c, id);
		else
			u = AmministratoreDao.getAmministratore(c, id);
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	public Persona updatePersona(int id) {
		Connection c = DbConnection.Connect();
		if(this instanceof Utente)
			UtenteDao.updateUtente(c, id, (Utente)this);
		else if(this instanceof Azienda)
			AziendaDao.updateAzienda(c, id, (Azienda)this);
		else
			AmministratoreDao.updateAmministratore(c, id, ((Amministratore)this));
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
}
