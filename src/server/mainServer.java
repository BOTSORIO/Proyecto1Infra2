package server;

import java.io.IOException;
import java.util.ArrayList;

import persistencia.*;

public class mainServer {

	private static EchoTCPServer es;

	public static void main(String[] args) {

		try {
			es = new EchoTCPServer();
			es.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String leerOpcionCliente() {
		
		String message = "";
		int numeroCuenta=0;
		double valor =0.0;
		String respuesta="";
		String tipo;
		String numeroApuesta="";

		try {
			message = EchoTCPServerProtocol.fromNetwork.readLine();
			System.out.println("[Server] From client: " + message);

			if (!message.equalsIgnoreCase("diez")) {

				String[] datos = message.split(",");

				String opcion = datos[0];
				System.out.println(opcion);
				
				switch (opcion) {
				
				case "CREAR_CUENTA":

					respuesta = EchoTCPServerProtocol.abrirCuenta(datos[1]);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;

				case "CANCELAR":
					numeroCuenta = Integer.parseInt(datos[1]);
					respuesta = EchoTCPServerProtocol.cancelarCuenta(numeroCuenta);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;

				case "DEPOSITAR":
					numeroCuenta = Integer.parseInt(datos[1]);
					valor = Double.parseDouble(datos[2]);
					respuesta = EchoTCPServerProtocol.depositar(numeroCuenta, valor);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;
					
				case "APOSTAR":
					numeroCuenta = Integer.parseInt(datos[1]);
					tipo= datos[2];
					numeroApuesta =datos[3];
					
					respuesta = EchoTCPServerProtocol.apostar(numeroCuenta, tipo, numeroApuesta);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;

				case "RETIRAR":
					numeroCuenta = Integer.parseInt(datos[1]);
					valor = Double.parseDouble(datos[2]);
					
					respuesta = EchoTCPServerProtocol.retirar(numeroCuenta, valor);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;

				case "CONSULTAR_SALDO":

					numeroCuenta = Integer.parseInt(datos[1]);
					respuesta = EchoTCPServerProtocol.consultar(numeroCuenta);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;
					
				case "SORTEO":

					int numeroGanador = Integer.parseInt(datos[1]);
					
					//int numeroGanador = (int)(10000 * Math.random());
					respuesta = EchoTCPServerProtocol.sortearApuestas(numeroGanador);
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;
					
				case "REPORTAR":

					respuesta = EchoTCPServerProtocol.reporteApuestas();
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;
					
				case "CERRAR":
					
					respuesta= EchoTCPServerProtocol.cerrarApuestas();		
					EchoTCPServerProtocol.toNetwork.println(respuesta);
					break;

					
				case "CARGA":

					try {

						String nombreArchivo = datos[1];
						ArrayList<String> lineas = LeerArchivo.leerArchivo(nombreArchivo);
						EchoTCPServerProtocol.toNetwork.println(lineas);
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				
				} 
			}else {
				try {
					es.cerrarSocket();
				} catch (Exception e) {
					System.out.println("socket cerrado");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";

	}


}
