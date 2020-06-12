package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Utente;
import com.stoteam.conto.Conto;

public class ContoDao {

	public static void UpConto(Connection c, Conto co) {
		int idInt = co.getUtente().getIdIntestatario();
		String insert = "INSERT INTO conto (codice_conto, iban, saldo, saldo_contabile, id_intestatario) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setString(1, co.getCodice());
			ps.setString(2, co.getIban());
			ps.setDouble(3, co.getSaldo());
			ps.setDouble(4, co.getSaldoContabile());
			ps.setInt(5, idInt);
			ps.execute();
			co.setIdIntestatario(idInt);
			System.out.println("Utente Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Conto getConto(Connection c, int id) {
		Conto co = null;
		String query = "SELECT * FROM conto WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Utente p = UtenteDao.getUtente(c, rs.getInt("id_intestatario"));
			co = new Conto(rs.getString("codice"), rs.getString("iban"), p, rs.getDouble("saldo"));
			co.setId(rs.getInt("id"));
			co.setIdIntestatario(rs.getInt("id_intestatario"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}
	public static int getIdConto(Connection c, int codice_conto) {
		int id = 0;
		String query = "SELECT * FROM utente WHERE codice_conto = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, codice_conto);
			ResultSet rs = ps.executeQuery();
			id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
}
