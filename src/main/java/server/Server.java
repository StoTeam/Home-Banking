package server;
/**
 * StaBanca! main server
 * @author Gianluca Tiribelli
 *
 */
public class Server {

	public static void main(String[] args) {

		Connect c = new Connect();
		c.listenSocket();
	}

}
