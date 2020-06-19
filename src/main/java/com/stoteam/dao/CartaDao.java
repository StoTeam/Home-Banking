package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
			ps.setTimestamp(3, b.getDataRilascio());
			ps.setTimestamp(4, b.getDataScadenza());
			ps.setString(5, b.getCodSicurezza());
			ps.setString(6, b.getPin());
			ps.setInt(10, b.getUtente().getId());
			ps.setNull(7, java.sql.Types.DOUBLE);
			ps.setNull(8, java.sql.Types.DOUBLE);
			ps.setNull(9, java.sql.Types.BOOLEAN);
			if(b instanceof CCredito) {
				CCredito cc = (CCredito) b;
				ps.setDouble(7, cc.getLimite());
				ps.setDouble(8, cc.getDisponibilita());
				ps.setBoolean(9, cc.isUsoPin());
			}
			ps.execute();
			b.setId(getIdCarta(c, b.getUtente().getId(), b.getDataRilascio()));
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
			rs.next();
//			rs.getDouble("limite");
			if(rs.getDouble("limite") == 0){
				b = new Bancomat(ContoDao.getConto(c, rs.getInt("conto_id")), rs.getString("pin"), rs.getString("codice_sicurezza"));
			System.out.println("limite uguale a 0");
			} else {
				b = (CCredito) new CCredito(ContoDao.getConto(c, rs.getInt("conto_id")), rs.getString("pin"), rs.getString("codice_sicurezza"), rs.getDouble("limite"));	
			System.out.println("limite maggiore di 0");
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

	public static int getIdCarta(Connection c, int contoId, Timestamp dataRilascio) {
		int id = 0;
		String query = "SELECT * FROM carta WHERE conto_id = ? AND data_rilascio = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, contoId);
			ps.setTimestamp(2, dataRilascio);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
				System.out.println("id = " + id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static void removeBancomat(Connection c, int id) {
		String deleteU = "DELETE FROM carta WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(deleteU);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Carta eliminata");
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
