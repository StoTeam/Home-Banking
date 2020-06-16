package com.stoteam.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bson.Document;

import com.stoteam.attori.Utente;
import com.stoteam.dao.UtenteDao;

public class Login {
	
	public static Document checkPass(Connection c, String email, String password) {
		String query = "SELECT * FROM utente WHERE email = ?;";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.getString("pass").equals(password)) {
				Utente u = UtenteDao.getUtente(c, rs.getInt("id"));
				Document d = u.utenteToDocument();
				return d;
			}
			System.err.println("Password Errata!");
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
}
