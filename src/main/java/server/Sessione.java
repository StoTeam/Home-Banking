package server;

import java.io.*;
import org.bson.Document;
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
				Document comando = Document.parse(line);
				String com = comando.getString("comando");
				controllo(com);
				//Send data back to client
				out.writeBytes(line);
			}catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}
		}
	}
	
	public boolean controllo(String com) {
		switch(com) {
		case "new_bonifico":
			System.out.println("Eseguo bonifico");
			return true;
			
			
		default:
			System.out.println("Non trovato");
			return false;
		}
	}
	
}

