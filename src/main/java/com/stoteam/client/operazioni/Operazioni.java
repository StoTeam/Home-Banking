package com.stoteam.client.operazioni;

import org.bson.Document;

public class Operazioni {

	public static String newBonifico(String iban, double importo, String causale) {
		Document params = new Document();
		params.append("azione", "new_bonifico");
		params.append("iban", iban);
		params.append("importo", importo);
		params.append("causale", causale);
		return params.toJson();
	}
	
}
