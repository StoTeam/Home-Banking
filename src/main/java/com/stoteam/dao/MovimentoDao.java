package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.stoteam.attori.Azienda;
import com.stoteam.attori.Utente;
import com.stoteam.carte.Bancomat;
import com.stoteam.conto.Conto;
import com.stoteam.movimenti.Bonifico;
import com.stoteam.movimenti.Deposito;
import com.stoteam.movimenti.Movimento;
import com.stoteam.movimenti.Pagamento;
import com.stoteam.movimenti.Prelievo;

public class MovimentoDao {

	public static void UpMovimento(Connection c, Movimento m) {
		String insert = "INSERT INTO movimento_conto (tipo_movimento, importo, conto_id_m, data_esecuzione, conto_id_d, data_arrivo, causale, carta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setString(1, m.getTipoMovimento());
			ps.setDouble(2, m.getImporto());
			ps.setInt(3, m.getConto().getId());
			ps.setTimestamp(4, m.getDataEsecuzione());
			ps.setNull(5, java.sql.Types.INTEGER);
			ps.setNull(6, java.sql.Types.TIMESTAMP);
			ps.setNull(7, java.sql.Types.VARCHAR);
			ps.setNull(8, java.sql.Types.INTEGER);
			if(m instanceof Bonifico) {
				Bonifico b = (Bonifico) m;
				ps.setInt(5, b.getDestinatario().getId());
				ps.setTimestamp(6, b.getDataArrivo());
				ps.setString(7, b.getCausale());
				ps.setNull(8, java.sql.Types.INTEGER);
			} else if(m instanceof Pagamento) {
				Pagamento p = (Pagamento) m;
				ps.setInt(5, p.getDestinatario().getId());
				ps.setNull(6, java.sql.Types.VARCHAR);
				ps.setNull(7, java.sql.Types.VARCHAR);
				ps.setInt(8, p.getCarta().getId());
			}
			ps.execute();
			m.setId(getIdMovimento(c, m.getDataEsecuzione()));
			System.out.println("Movimento Salvato");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Movimento getMovimento(Connection c, int id) {
		Movimento m = null;
		String query = "SELECT * FROM movimento_conto WHERE id = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Conto mittente = ContoDao.getConto(c, rs.getInt("conto_id_m"));
				String tipoMovimento = rs.getString("tipo_movimento");
				if(tipoMovimento.equals("bonifico")) {
					Conto destinatario = ContoDao.getConto(c, rs.getInt("conto_id_d"));
					m = new Bonifico(rs.getDouble("importo"), mittente, "bonifico", destinatario, rs.getString("causale"));
					m.setDataEsecuzione(rs.getString("data_esecuzione"));
					((Bonifico) m).setDataArrivo(rs.getString("data_arrivo"));
				} else if(tipoMovimento.equals("pagamento")) {
					Conto destinatario = ContoDao.getConto(c, rs.getInt("conto_id_d"));
					m = new Pagamento(mittente, rs.getDouble("importo"), "pagamento", destinatario,  CartaDao.getCarta(c, rs.getInt("carta_id")));
				} else if(tipoMovimento.equals("deposito")) {
					m = new Deposito(mittente, tipoMovimento, rs.getDouble("importo"));
				} else if(tipoMovimento.equals("prelievo")) {
					m = new Prelievo(mittente, rs.getDouble("importo"), tipoMovimento);
				}
				m.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	public static void removeMovimento(Connection c, int id) {
		String deleteM = "DELETE FROM movimento_conto WHERE id = ?";
		Movimento idInt = MovimentoDao.getMovimento(c, id);
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(deleteM);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Movimento eliminato");
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	public static int getIdMovimento(Connection c, Timestamp dataEsecuzione) {
		int id = 0;
		String query = "SELECT id FROM movimento_conto WHERE data_esecuzione = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setTimestamp(1, dataEsecuzione);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
				System.out.println("ID trovato: " + id);
			}
			System.out.println(dataEsecuzione);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
