package com.stoteam.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.bson.Document;

import com.stoteam.attori.Utente;

public class ClientConn {
	Socket client;
	
	public void connect() {
		try {
			client = new Socket("localhost", 4444);
			System.out.println("Connesso");
		} catch (IOException e) {
			System.err.println("Impossibile connettere sulla porta 4444");
			e.printStackTrace();
		}
		try(DataOutputStream dos = new DataOutputStream(client.getOutputStream()); DataInputStream br = new DataInputStream(client.getInputStream())){
			  BufferedReader kb 
	            = new BufferedReader( 
	                new InputStreamReader(System.in)); 
	        String str; 
	        Document doc = new Document().append("comando", "new_bonifico");
	        System.out.println(doc.toJson());
	        while (!(str = kb.readLine()).equals("exit")) { 
	  
	            // send to the server 
	            dos.writeUTF(doc.toJson());
	            dos.flush();
	  
	            // receive from the server 
	            String line = br.readUTF();
				System.out.println(line);
				//{auth="aaazzzzpppppeeee",comando="new_bonifico",params:{destinatario="iban",quantita="1000"}}
				Document u = Document.parse(line);
				Utente utente = new Utente(u.getString("nome"), u.getString("cognome"), u.getString("telefono"), u.getString("email"), u.getString("password"), u.getInteger("tipoUtente"), u.getString("indirizzo"), u.getString("codiceFiscale"));
				
	            System.out.println(utente.toString()); 
	        } 
	        kb.close();
	        client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}