package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.bson.Document;

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
	        String str, str1; 
	        Document doc = new Document().append("comando", "new_bonifico");
	        System.out.println(doc.toJson());
	        while (!(str = kb.readLine()).equals("exit")) { 
	  
	            // send to the server 
	            dos.writeUTF(doc.toJson());
	            dos.flush();
	  
	            // receive from the server 
	            String stringa = br.readUTF(); 
	  
	            System.out.println(stringa); 
	        } 
	        kb.close();
	        client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
