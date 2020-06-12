package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stoteam.attori.Azienda;
import com.stoteam.conto.Conto;
import com.stoteam.movimenti.Bonifico;
import com.stoteam.movimenti.Deposito;
import com.stoteam.movimenti.Movimento;
import com.stoteam.movimenti.Pagamento;
import com.stoteam.movimenti.Prelievo;

public class MovimentoDao {

	public static void UpMovimento(Connection c, Movimento m) {
		String insert = "INSERT INTO movimenti_conto (tipo_movimento, importo, conto_id_m, data_esecuzione, conto_id_d, data_arrivo, causale, carta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(insert);
			ps.setString(1, m.getTipoMovimento());
			ps.setDouble(2, m.getImporto());
			ps.setInt(3, m.getConto().getId());
			ps.setString(4, m.getDataEsecuzione());
			ps.setInt(5, -1);
			ps.setString(6, "");
			ps.setString(7, "");
			ps.setInt(8, -1);
			if(m instanceof Bonifico) {
				Bonifico b = (Bonifico) m;
				ps.setInt(5, b.getDestinatario().getId());
				ps.setString(6, b.getDataArrivo());
				ps.setString(7, b.getCausale());
				ps.setInt(8, -1);
			} else if(m instanceof Pagamento) {
				Pagamento p = (Pagamento) m;
				ps.setInt(5, p.getDestinatario().getId());
				ps.setString(6, "");
				ps.setString(7, "");
				ps.setInt(8, p.getConto().getId());
			}
			ps.execute();
			System.out.println("Utente Salvato");
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
			Conto mittente = ContoDao.getConto(c, rs.getInt("conto_id_m"));
			String tipoMovimento = rs.getString("tipo_movimento");
			if(tipoMovimento.equals("bonifico")) {
				Conto destinatario = ContoDao.getConto(c, rs.getInt("conto_id_d"));
				m = new Bonifico(rs.getDouble("importo"), mittente, "bonifico", destinatario, rs.getString("causale"));
			} else if(tipoMovimento.equals("pagamento")) {
				Conto destinatario = ContoDao.getConto(c, rs.getInt("conto_id_d"));
				//m = new Pagamento(rs.getDouble("importo"), mittente, "pagamento", destinatario, carta.getId());
			} else if(tipoMovimento.equals("deposito")) {
				m = new Deposito(mittente, tipoMovimento, rs.getDouble("importo"));
			} else if(tipoMovimento.equals("prelievo")) {
				m = new Prelievo(mittente, rs.getDouble("importo"), tipoMovimento);
			}
			m.setId(rs.getInt("id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
