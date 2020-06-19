package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntestatarioDao {
	
	public static int createIntestatario(Connection c) {
		String insert = "INSERT INTO intestatario () VALUES ()";
		PreparedStatement ps = null;
		String query = "SELECT * FROM intestatario ORDER BY id DESC LIMIT 1";
		PreparedStatement qry = null;
		int idInt = 0;
		try {
			ps = c.prepareStatement(insert);
			ps.executeUpdate();
			qry = c.prepareStatement(query);
			ResultSet rs = qry.executeQuery();
			rs.next();
			idInt = rs.getInt("id");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idInt;
	}
}
