package tcp;

import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket;

	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			EchoTCPServerProtocol.protocol(socket);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
