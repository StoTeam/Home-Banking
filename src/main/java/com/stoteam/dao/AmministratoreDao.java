/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Amministratore;
/**
 * @param UpAmministratore - Inserimento nel database dei dati dell'amministratore 
 * (nome, cognome, telefono, email, password, tipo utente, indirizzo, livello accesso e area competenza)
 * @return Dati Amministratore
 */
public class AmministratoreDao {

	public static void UpAmministratore(Connection c, Amministratore a) {
		String insert = "INSERT INTO amministratore (nome, cognome, telefono, email, pass, tipo_utente, indirizzo, livello_accesso, area_competenza) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setString(1, a.getNome());
			ps.setString(2, a.getCognome());
			ps.setString(3, a.getTelefono());
			ps.setString(4, a.getEmail());
			ps.setString(5, a.getPassword());
			ps.setInt(6, a.getTipoUtente());
			ps.setString(7, a.getIndirizzo());
			ps.setString(8, a.getLivelloAccesso());
			ps.setString(9, a.toCSV());
			ps.execute();
			a.setId(AmministratoreDao.getIdAmministratore(c, a.getEmail()));
			System.out.println("Amministratore Salvato");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param getAmministratore - Ottiene dal database i dati dell'amministratore
	 * (nome, cognome, telefono, email, password, tipo utente, indirizzo, livello accesso e area competenza)
	 * @return Dati Amministratore
	 */
	public static Amministratore getAmministratore(Connection c, int id) {
		Amministratore a = null;
		String query = "SELECT * FROM utente WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){				
				a = new Amministratore(rs.getString("nome"), rs.getString("cognome"),
						rs.getString("telefono"), rs.getString("email"), rs.getString("pass"),
						rs.getInt("tipo_utente"), rs.getString("indirizzo"), rs.getString("livello_accesso"), a.CSVToList(rs.getString("area_competenza")));
				a.setId(rs.getInt("id"));
				System.out.println("amministratore instanziato" + a.getCognome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	/**
	 * @param getIdAmministratore - Ottiene dal database l'ID dell'amministratore
	 * @return ID Amministratore
	 */
	public static int getIdAmministratore(Connection c, String email) {
		int id = 0;
		String query = "SELECT id FROM amministratore WHERE email = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * @param removeAmministratore - Rimuove dal database l'amministratore
	 * @return Amministratore
	 */
	public static void removeAmministratore(Connection c, int id) {
		String deleteU = "DELETE FROM amministratore WHERE id = ?" ;
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(deleteU);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Amministratore eliminato");
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param updateAmministratore - Aggiorna nel database i dati dell'amministratore 
	 * (nome, cognome, telefono, email, password, indirizzo, livello accesso e area competenza)
	 * @return Dati Amministratore
	 */
	public static void updateAmministratore(Connection c, int id, Amministratore newAmministratore) {
		Amministratore utenteDB = AmministratoreDao.getAmministratore(c, id);
		String update = "UPDATE amministratore SET nome = ?, cognome = ?, telefono = ?, email = ?, pass = ?, indirizzo = ?, livello_accesso = ?, area_competenza = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, newAmministratore.getNome());
			ps.setString(2, newAmministratore.getCognome());
			ps.setString(3, newAmministratore.getTelefono());
			ps.setString(4, newAmministratore.getEmail());
			ps.setString(5, newAmministratore.getPassword());
			ps.setString(6, newAmministratore.getIndirizzo());
			ps.setString(7, newAmministratore.getLivelloAccesso());
			ps.setString(8, newAmministratore.toCSV());
			ps.setInt(9, utenteDB.getId());
			ps.execute();
			System.out.println("Amministratore Aggiornato");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

