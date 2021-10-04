package tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {
	public static final int PORT = 3400;

	private ServerSocket listener;
	private Socket serverSideSocket;
	private ServerThread hilo;

	public EchoTCPServer() {
		System.out.println("Echo TCP server is running on port: " + PORT);
	}

	public void init() throws Exception {

		listener = new ServerSocket(PORT);

		while (true) {
			serverSideSocket = listener.accept();
			hilo = new ServerThread(serverSideSocket);
			hilo.run();

		}

	}

	public static void main(String args[]) throws Exception {
		EchoTCPServer es = new EchoTCPServer();
		es.init();
	}

}
