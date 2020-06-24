/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Persona;
import com.stoteam.conto.Conto;

public class ContoDao {
	
	/**
	 * @param UpConto - Inserimento nel database dei dati del conto (codice_conto, iban, saldo, saldo contabile, id intestatario)
	 * @return Conto
	 */
	
	public static void UpConto(Connection c, Conto co, int idInt) {
		System.out.println(co.toString());
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
			co.setId(getIdConto(c, co.getCodice()));
			System.out.println("Conto Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param getConto - Ottiene dal database i dati del conto (codice_conto, iban, saldo, saldo contabile, ID intestatario)
	 * @return Conto
	 */
	
	public static Conto getConto(Connection c, int id) {
		Conto co = null;
		Persona p = null;
		String query = "SELECT * FROM conto WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(!GeneralDao.azOrPers(c, rs.getInt("id_intestatario"))) {
					p = UtenteDao.getUtente(c, rs.getInt("id"));
				} else {
					p = AziendaDao.getAzienda(c, rs.getInt("id"));
				}
				co = new Conto(rs.getString("codice_conto"), rs.getString("iban"), p, rs.getDouble("saldo"));
				co.setId(rs.getInt("id"));
				co.setIdIntestatario(rs.getInt("id_intestatario"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}
	
	/**
	 * @param getConto - Ottiene dal database l'ID del conto
	 * @return ID Conto
	 */
	
	public static int getIdConto(Connection c, String codice_conto) {
		int id = 0;
		String query = "SELECT * FROM conto WHERE codice_conto = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, codice_conto);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * @param getIdContoByIban - Ottiene dal database l'ID del conto dall'IBAN
	 * @return ID Conto
	 */
	
	public static int getIdContoByIban(Connection c, String iban) {
		int id = 0;
		String query = "SELECT * FROM conto WHERE iban = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, iban);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				id = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * @param removeConto - Rimuove dal database il conto
	 * @return Conto
	 */
	
	public static void removeConto(Connection c, int id) {
		String deleteU = "DELETE FROM conto WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(deleteU);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Conto eliminato");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param updateConto - Aggiornamento sul database dei dati del conto (codice_conto, iban, saldo, saldo contabile, id intestatario)
	 * @return Conto
	 */
	
	public static void updateConto(Connection c, int id, Conto newConto) {
		Conto contoDB = ContoDao.getConto(c, id);
		String update = "UPDATE conto SET codice_conto = ?, iban = ?, saldo = ?, saldo_contabile = ? WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(update);
			ps.setString(1, newConto.getCodice());
			ps.setString(2, newConto.getIban());
			ps.setDouble(3, newConto.getSaldo());
			ps.setDouble(4, newConto.getSaldoContabile());
			ps.setInt(5, id);
			ps.execute();
			System.out.println("Conto Aggiornato");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
