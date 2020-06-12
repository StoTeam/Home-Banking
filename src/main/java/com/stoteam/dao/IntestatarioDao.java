package com.stoteam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntestatarioDao {
	
	public static int createIntestatario(Connection c) {
		String insert = "INSERT INTO intestatario";
		PreparedStatement ps = null;
		String query = "SELECT TOP 1 * FROM intestatario ORDER BY id DESC";
		PreparedStatement qry = null;
		int idInt = 0;
		try {
			ps = c.prepareStatement(insert);
			ps.execute();
			qry = c.prepareStatement(query);
			ResultSet rs = qry.executeQuery();
			idInt = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idInt;
	}
}
