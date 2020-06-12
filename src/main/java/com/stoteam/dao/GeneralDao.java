package com.stoteam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GeneralDao {

	public static void update(Connection c, String tabella, String colonna, double modifica, int id) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("saldo") || colonna.equals("saldo_contabile")) {
			String update = "UPDATE ? SET ? = ?, WHERE id = ?;";
			PreparedStatement ps = null;
			try {
				ps = c.prepareStatement(update);
				ps.setString(1, colonna);
				ps.setString(2, tabella);
				ps.setDouble(3, modifica);
				ps.setInt(4, id);
				ps.execute();
				System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
	public static void update(Connection c, String tabella, String colonna, int modifica, int id) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("saldo") || colonna.equals("saldo_contabile")) {
			String update = "UPDATE ? SET ? = ?, WHERE id = ?;";
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
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
	public static void update(Connection c, String tabella, String colonna, String modifica, int id) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("saldo") || colonna.equals("saldo_contabile")) {
			String update = "UPDATE ? SET ? = ?, WHERE id = ?;";
			PreparedStatement ps = null;
			try {
				ps = c.prepareStatement(update);
				ps.setString(1, colonna);
				ps.setString(2, tabella);
				ps.setString(3, modifica);
				ps.setInt(4, id);
				ps.execute();
				System.out.println("Colonna: " + colonna + " | Aggiornata a: " + modifica);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
	public static void update(Connection c, String tabella, String colonna, boolean modifica, int id) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("saldo") || colonna.equals("saldo_contabile")) {
			String update = "UPDATE ? SET ? = ?, WHERE id = ?;";
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
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
	public static void update(Connection c, String tabella, String colonna, Date modifica, int id) {
		colonna = colonna.toLowerCase();
		if(colonna.equals("saldo") || colonna.equals("saldo_contabile")) {
			String update = "UPDATE ? SET ? = ?, WHERE id = ?;";
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
		} else {
			throw new IllegalArgumentException("Non puoi modificare il campo: " + colonna);
		}
	}
}
