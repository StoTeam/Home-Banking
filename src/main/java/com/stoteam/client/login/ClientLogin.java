package com.stoteam.client.login;

import org.bson.Document;

public class ClientLogin {

	public static String doLogin(String email, String password) {
		Document params = new Document();
		params.append("azione", "login");
		params.append("email", email);
		params.append("password", password);
		return params.toJson();
	}
	
}
