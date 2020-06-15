package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Utente;

public class UtenteDao {
	
	public static void UpUtente(Connection c, Utente u) {
		int idInt = IntestatarioDao.createIntestatario(c);
		String insert = "INSERT INTO utente (nome, cognome, telefono, email, pass, tipo_utente, indirizzo, codice_fiscale, id_intestatario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setString(1, u.getNome());
			ps.setString(2, u.getCognome());
			ps.setString(3, u.getTelefono());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setInt(6, u.getTipoUtente());
			ps.setString(7, u.getIndirizzo());
			ps.setString(8, u.getCodiceFiscale());
			ps.setInt(9, idInt);
			ps.execute();
			u.setIdIntestatario(idInt);
			u.setId(UtenteDao.getIdUtente(c, u.getCodiceFiscale()));
			System.out.println("Utente Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Utente getUtente(Connection c, int id) {
		Utente u = null;
		String query = "SELECT * FROM utente WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getString("email"), rs.getString("pass"), rs.getInt("tipo_utente"), rs.getString("indirizzo"), rs.getString("codice_fiscale"));
				u.setId(rs.getInt("id"));
				u.setIdIntestatario(rs.getInt("id_intestatario"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public static int getIdUtente(Connection c, String cf) {
		int id = 0;
		String query = "SELECT * FROM utente WHERE codice_fiscale = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	public static void removeUtente(Connection c, int id) {
		String deleteU = "DELETE FROM utente WHERE id = " + id;
		int idInt = UtenteDao.getUtente(c, id).getIdIntestatario();
		String deleteI = "DELETE FROM intestatario WHERE id = " + idInt;
		PreparedStatement ps = null;
		PreparedStatement psI = null;
		try {
			ps = c.prepareStatement(deleteU);
			ps.execute();
			psI = c.prepareStatement(deleteI);
			psI.execute();
			System.out.println("Utente eliminato");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
