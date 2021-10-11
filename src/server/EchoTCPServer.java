package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {

	public static final int PORT = 3400;

	private ServerSocket listener;
	private Socket serverSideSocket;

	public EchoTCPServer() {
		System.out.println("Echo TCP server is running on port: " + PORT);
	}

	public void init() throws Exception {

		try {
			listener = new ServerSocket(PORT);

			while (true) {
				serverSideSocket = listener.accept();
				EchoTCPServerProtocol.protocol(serverSideSocket);

			}
		} catch (Exception e) {
			System.out.println("Conexion terminada");
		}

	}

	public void cerrarSocket() throws IOException {
		listener.close();
	}
}
