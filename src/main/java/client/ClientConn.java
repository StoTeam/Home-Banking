package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
		try(DataOutputStream dos = new DataOutputStream(client.getOutputStream()); BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))){
			  BufferedReader kb 
	            = new BufferedReader( 
	                new InputStreamReader(System.in)); 
	        String str, str1; 
	  
	        // repeat as long as exit 
	        // is not typed at client 
	        while (!(str = kb.readLine()).equals("exit")) { 
	  
	            // send to the server 
	            dos.writeBytes(str + "\n"); 
	  
	            // receive from the server 
	            str1 = br.readLine(); 
	  
	            System.out.println(str1); 
	        } 
	        kb.close();
	        client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
