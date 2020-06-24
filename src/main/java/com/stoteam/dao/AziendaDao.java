package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Future;

import com.stoteam.attori.Amministratore;
import com.stoteam.attori.Azienda;

public class AziendaDao {
	/**
	 * @param UpAzienda - Inserisce nel database i dati dell'azienda 
	 * (nome, cognome, telefono, email, password, tipo utente, indirizzo, ragione sociale, partita IVA e ID intestatario)
	 * @return Dati Azienda
	 */
	public static Future<Void> UpAzienda(Connection c, Azienda a) {
		int idInt = IntestatarioDao.createIntestatario(c);
		String insert = "INSERT INTO aziende (nome, cognome, telefono, email, passw, tipo_utente, indirizzo, ragione_sociale, partita_iva, id_intestatario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
			ps.setString(8, a.getRagioneSociale());
			ps.setString(9, a.getpIva());
			ps.setInt(10, idInt);
			ps.execute();
			a.setIdIntestatario(idInt);
			a.setId(AziendaDao.getIdAzienda(c, a.getpIva()));
			System.out.println("Azienda Salvata");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param getAzienda - Ottiene dal database i dati dell'azienda 
	 * (nome, cognome, telefono, email, password, tipo utente, indirizzo, ragione sociale, partita IVA e ID intestatario)
	 * @return Dati Azienda
	 */
	public static Azienda getAzienda(Connection c, int id) {
		Azienda u = null;
		String query = "SELECT * FROM aziende WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new Azienda(rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getString("email"), rs.getString("passw"), rs.getInt("tipo_utente"), rs.getString("indirizzo"), rs.getString("ragione_sociale"), rs.getString("partita_iva"));
				u.setId(rs.getInt("id"));
				u.setIdIntestatario(rs.getInt("id_intestatario"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	/**
	 * @param getIdAzienda - Ottiene dal database l'ID dell'azienda 
	 * @return ID Azienda
	 */
	public static int getIdAzienda(Connection c, String pIva) {
		int id = 0;
		String query = "SELECT * FROM aziende WHERE partita_iva = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, pIva);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * @param removeAzienda - Rimuove dal database l'azienda 
	 * @return Azienda
	 */
	public static void removeAzienda(Connection c, int id) {
		String deleteU = "DELETE FROM aziende WHERE id = " + id;
		int idInt = AziendaDao.getAzienda(c, id).getIdIntestatario();
		String deleteI = "DELETE FROM intestatario WHERE id = " + idInt;
		PreparedStatement ps = null;
		PreparedStatement psI = null;
		try {
			ps = c.prepareStatement(deleteU);
			ps.execute();
			psI = c.prepareStatement(deleteI);
			psI.execute();
			System.out.println("Azienda eliminata");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param updateAzienda - Aggiorna sul database i dati dell'azienda 
	 * (nome, cognome, telefono, email, password, tipo utente, indirizzo, ragione sociale, partita IVA e ID intestatario)
	 * @return Dati Azienda
	 */
	public static void updateAzienda(Connection c, int id, Azienda newAzienda) {
		Amministratore utenteDB = AmministratoreDao.getAmministratore(c, id);
		String update = "UPDATE aziende SET nome = ?, cognome = ?, telefono = ?, email = ?, pass = ?, indirizzo = ?, ragione_sociale = ?, partita_iva = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, newAzienda.getNome());
			ps.setString(2, newAzienda.getCognome());
			ps.setString(3, newAzienda.getTelefono());
			ps.setString(4, newAzienda.getEmail());
			ps.setString(5, newAzienda.getPassword());
			ps.setString(6, newAzienda.getIndirizzo());
			ps.setString(7, newAzienda.getpIva());
			ps.setString(8, newAzienda.getRagioneSociale());
			ps.setInt(9, utenteDB.getId());
			ps.execute();
			System.out.println("Azienda Aggiornata");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
