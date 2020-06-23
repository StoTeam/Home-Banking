package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.stoteam.attori.Persona;
import com.stoteam.conto.Conto;
import com.stoteam.conto.Prestito;

public class PrestitoDao {

	public static void UpPrestito(Connection c, Prestito prestito) {
		String insert = "INSERT INTO prestito (importo, dovuti,"
				+ "pagati, tan, taeg, tempo, is_fisso,"
				+ "is_approvato, data_inizio, data_fine,"
				+ "data_rata, conto_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setDouble(1, prestito.getImporto());
			ps.setDouble(2, prestito.getDovuti());
			ps.setDouble(3, prestito.getPagati());
			ps.setDouble(4, prestito.getTan());
			ps.setDouble(5, prestito.getTaeg());
			ps.setInt(6, prestito.getTempo());
			ps.setBoolean(7, prestito.getIsFisso());
			ps.setBoolean(8, prestito.getIsApprovato());
			ps.setTimestamp(9, prestito.getDataInizio());
			ps.setTimestamp(10, prestito.getDataFine());
			ps.setTimestamp(11, prestito.getDataRata());
			ps.setInt(12, prestito.getConto().getId());
			ps.execute();
			prestito.setId(getIdPrestito(c, prestito.getConto().getId(), prestito.getDataInizio()));
			System.out.println("Prestito Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Prestito getPrestito(Connection c, int id) {
		Prestito prestito = null;
		Conto co = null;
		String query = "SELECT * FROM prestito WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			prestito = new Prestito(rs.getDouble("importo"),
					rs.getDouble("tan"),
					rs.getDouble("taeg"),  rs.getInt("tempo"),
					rs.getBoolean("is_fisso"),
					ContoDao.getConto(c, rs.getInt("conto_id")));
			prestito.setId(rs.getInt("id"));
			prestito.setDovuti(rs.getDouble("dovuti"));
			prestito.setPagati(rs.getDouble("pagati"));
			prestito.setIsApprovato(rs.getBoolean("is_approvato"));
			prestito.setDataInizio(rs.getTimestamp("data_inizio"));
			prestito.setDataFine(rs.getTimestamp("data_fine"));
			prestito.setDataRata(rs.getTimestamp("data_rata"));
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prestito;
}
public static int getIdPrestito(Connection c, int contoId, Timestamp dataInizio) {
	int id = 0;
	String query = "SELECT * FROM prestito WHERE conto_id = ? AND data_inizio = ?;";
	PreparedStatement ps = null;
	try {
		ps = c.prepareStatement(query);
		ps.setInt(1, contoId);
		ps.setTimestamp(2, dataInizio);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			id = rs.getInt("id");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id;
}

public static void removePrestito(Connection c, int id) {
	String deleteU = "DELETE FROM prestito WHERE id = ?";
	PreparedStatement ps = null;
	try {
		ps = c.prepareStatement(deleteU);
		ps.setInt(1, id);
		ps.execute();
		System.out.println("Prestito eliminato");
	} catch(SQLException e) {
		e.printStackTrace();
	}
}

}
