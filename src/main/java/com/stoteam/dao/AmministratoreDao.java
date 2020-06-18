package com.stoteam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.stoteam.attori.Amministratore;

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
				System.out.println("ID trovato: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
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

}

