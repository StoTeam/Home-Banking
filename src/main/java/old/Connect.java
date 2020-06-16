package old;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class Connect {

	ServerSocket server;

	public void listenSocket(){

		try{
			server = new ServerSocket(4444);
			System.out.println("listen on port 4444");
		} catch (IOException e) {
			System.out.println("Could not listen on port 4444");
			System.exit(-1);
		}
		while(true){
			Sessione w;
			try{
				//server.accept returns a client connection
				w = new Sessione(server.accept(), "");
				Thread t = new Thread(w);
				t.start();
				System.out.println("Nuova Sessione");
			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}
		}
	}
	protected void finalize(){
		//Objects created in run method are finalized when
		//program terminates and thread exits
		try{
			server.close();
		} catch (IOException e) {
			System.out.println("Could not close socket");
			System.exit(-1);
		}
	}
}
