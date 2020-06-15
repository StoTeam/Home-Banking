package com.stoteam.server;

import java.io.*;
import org.bson.Document;

import com.stoteam.attori.Utente;
import com.stoteam.dao.DbConnection;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.net.ServerSocket;

public class Sessione implements Runnable {
	private Socket client;
	private String text;

	//Constructor
	Sessione(Socket client, String text) {
		this.client = client;
		this.text = text;
	}
	public void run(){
		//String line;
		ControlloIngressi ci = new ControlloIngressi();
		DataInputStream in = null;
		DataOutputStream out = null;
		try{
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			System.out.println("in or out failed");
			System.exit(-1);
		}
		while(true){
			try{
				String line = in.readUTF();
				System.out.println(line);
				//{auth="aaazzzzpppppeeee",comando="new_bonifico",params:{destinatario="iban",quantita="1000"}}
				Document risposta = ci.controllo(DbConnection.Connect(), line);
				//Send data back to client
				
				out.writeUTF(risposta.toJson());
			}catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}
		}
	}	
}

