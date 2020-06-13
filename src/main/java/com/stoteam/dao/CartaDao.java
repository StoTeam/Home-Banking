package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.carte.Bancomat;
import com.stoteam.carte.CCredito;

public class CartaDao {

	public static void UpCarta(Connection c, Bancomat b) {
		String insert = "INSERT INTO carta (is_block, spesa_mensile, data_rilascio, data_scadenza, codice_sicurezza, pin, limite, disponibilita, uso_pin, conto_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setBoolean(1, b.isBlock());
			ps.setDouble(2, b.getSpesaMensile());
			ps.setString(3, b.getDataRilascio().toString());
			ps.setString(4, b.getDataScadenza().toString());
			ps.setString(5, b.getCodSicurezza());
			ps.setString(6, b.getPin());
			ps.setInt(10, b.getUtente().getId());
			ps.setDouble(7, -1);
			ps.setDouble(8, -1);
			ps.setBoolean(9, false);
			if(b instanceof CCredito) {
				CCredito cc = (CCredito) b;
				ps.setDouble(7, cc.getLimite());
				ps.setDouble(8, cc.getDisponibilita());
				ps.setBoolean(9, cc.isUsoPin());
			}
			ps.execute();
			System.out.println("Carta Salvata");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Bancomat getCarta(Connection c, int id) {
		Bancomat b = null;
		String query = "SELECT * FROM carta WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.getDouble("limite") == -1) {
				b = new Bancomat(ContoDao.getConto(c, rs.getInt("conto_id")), rs.getString("pin"), rs.getString("codice_sicurezza"));
			} else {
				b = (CCredito) new CCredito(ContoDao.getConto(c, rs.getInt("conto_id")), rs.getString("pin"), rs.getString("codice_sicurezza"), rs.getDouble("limite"));	
			}
			b.setId(rs.getInt("id"));
			b.setContoId(rs.getInt("conto_id"));
			b.setDataRilascio(rs.getString("data_rilascio"));
			b.setDataScadenza(rs.getString("data_scadenza"));
			b.setSpesaMensile(rs.getDouble("spesa_mensile"));
			b.setBlock(rs.getBoolean("is_block"));
			if(b instanceof CCredito) {
				((CCredito) b).setDisponibilita(rs.getDouble("disponibilita"));
				((CCredito) b).setUsoPin(rs.getBoolean("uso_pin"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
