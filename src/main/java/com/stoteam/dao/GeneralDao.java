package com.stoteam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralDao {

	public static void update(Connection c, String tabella, String colonna, double modifica, int id) {
		colonna = colonna.toLowerCase();
		String update = "UPDATE ? SET ? = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, tabella);
			ps.setString(2, colonna);
			ps.setDouble(3, modifica);
			ps.setInt(4, id);
			ps.execute();
			System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void update(Connection c, String tabella, String colonna, int modifica, int id) {
		colonna = colonna.toLowerCase();
		String update = "UPDATE ? SET ? = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, colonna);
			ps.setString(2, tabella);
			ps.setInt(3, modifica);
			ps.setInt(4, id);
			ps.execute();
			System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void update(Connection c, String tabella, String colonna, String modifica, int id) {
		colonna = colonna.toLowerCase();
		tabella = tabella.toLowerCase();
		String update = "UPDATE " + tabella + " SET " + colonna + "= ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, modifica);
			ps.setInt(2, id);
			ps.execute();
			System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void update(Connection c, String tabella, String colonna, boolean modifica, int id) {
		colonna = colonna.toLowerCase();

		String update = "UPDATE ? SET ? = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, colonna);
			ps.setString(2, tabella);
			ps.setBoolean(3, modifica);
			ps.setInt(4, id);
			ps.execute();
			System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void update(Connection c, String tabella, String colonna, Date modifica, int id) {
		colonna = colonna.toLowerCase();
		String update = "UPDATE ? SET ? = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, colonna);
			ps.setString(2, tabella);
			ps.setDate(3, modifica);
			ps.setInt(4, id);
			ps.execute();
			System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean azOrPers(Connection c, int idInt) {
		PreparedStatement ps = null;
		String query = "SELECT * FROM utente,azienda WHERE id_intestatario = ?;";
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, idInt);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.findColumn("partita_iva");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
