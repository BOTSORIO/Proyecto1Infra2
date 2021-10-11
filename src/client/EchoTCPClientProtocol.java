package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoTCPClientProtocol {
	
	
	public static PrintWriter toNetwork;
	public static BufferedReader fromNetwork;
	
	public static PrintWriter toNetwork2;
	public static BufferedReader fromNetwork2;
	
	public static void protocol(Socket socket) throws IOException
	{
		createStreams(socket);
		mainClient.mostrarMenu();		
	}

	public static void createStreams(Socket socket) throws IOException
	{
		toNetwork = new PrintWriter(socket.getOutputStream(),true);
		fromNetwork= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		toNetwork2 = new PrintWriter(socket.getOutputStream(),true);
		fromNetwork2= new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
     
}
