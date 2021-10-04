package tcp;


import java.net.Socket;

public class EchoTCPClient {
	public static final int PORT = 3400;
	public static final String SERVER = "localhost";

	private Socket clientSideSocket;

	public EchoTCPClient() {
		System.out.println("Echo TCP Client ...");
	}

	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);

		EchoTCPClientProtocol.protocol(clientSideSocket);
		clientSideSocket.close();

	}

	public static void main(String args[]) throws Exception {

		EchoTCPClient ec = new EchoTCPClient();
		ec.init();
	}
}
