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
			System.out.println("Utente Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Utente getUtente(Connection c, String cf) {
		Utente u = null;
		String query = "SELECT * FROM utente WHERE codice_fiscale = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, cf);
			ResultSet rs = ps.executeQuery();
			u = new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getString("email"), rs.getString("pass"), rs.getInt("tipo_utente"), rs.getString("indirizzo"), rs.getString("codice_fiscale"));
			u.setId(rs.getInt("id"));
			u.setIdIntestatario(rs.getInt("id_intestatario"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public static void updateUtente(Connection c, String colonna, String modifica, String cf) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("telefono") || colonna.equals("email") || colonna.equals("pass") || colonna.equals("indirizzo")) {
			String update = "UPDATE utente SET ? = ?, WHERE codice_fiscale = ?;";
			PreparedStatement ps = null;
			try {
				ps = c.prepareStatement(update);
				ps.setString(1, colonna);
				ps.setString(2, modifica);
				ps.setString(3, cf);
				ps.execute();
				System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
}
