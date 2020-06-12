package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Azienda;

public class AziendaDao {

	public static void UpUtente(Connection c, Azienda a) {
		int idInt = IntestatarioDao.createIntestatario(c);
		String insert = "INSERT INTO aziende (nome, cognome, telefono, email, pass, tipo_utente, indirizzo, ragione_sociale, partita_iva, id_intestatario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
			ps.setInt(9, idInt);
			ps.execute();
			a.setIdIntestatario(idInt);
			System.out.println("Utente Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Azienda getAzienda(Connection c, int idInt) {
		Azienda u = null;
		String query = "SELECT * FROM azienda WHERE id_intestatario = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, idInt);
			ResultSet rs = ps.executeQuery();
			u = new Azienda(rs.getString("nome"), rs.getString("cognome"), rs.getString("telefono"), rs.getString("email"), rs.getString("pass"), rs.getInt("tipo_utente"), rs.getString("indirizzo"), rs.getString("ragione_sociale"), rs.getString("partita_iva"));
			u.setId(rs.getInt("id"));
			u.setIdIntestatario(rs.getInt("id_intestatario"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public static int getIdAzienda(Connection c, String pIva) {
		int id = 0;
		String query = "SELECT * FROM azienda WHERE partita_iva = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, pIva);
			ResultSet rs = ps.executeQuery();
			id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
}
