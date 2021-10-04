package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoTCPClientProtocol {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String ESPACIO = " ";
	private static PrintWriter toNetwork;

	private static BufferedReader fromNetwork;

	public static void menuOpciones() {
		System.out.println("****************** Banco DyB ******************\n" + "\t0) Cargar datos.\n"
				+ "\t1) Crear Cuenta.\n" + "\t2) Depositar dinero en una cuenta.\n"
				+ "\t3) Retirar dinero de una cuenta.\n" + "\t4) Cancelar cuenta de ahorros.\n"
				+ "\t5) Crear un bolsillo.\n" + "\t6) Trasladar dinero a un bolsillo.\n" + "\t7) Cancelar bolsillo.\n"
				+ "\t8) Consultar saldo.\n" + "\t9) Salir.");
	}

	public static int leerOpcion() {
		int opcion;
		System.out.print("Ingrese la opcion aqui: ");
		opcion = SCANNER.nextInt();
		SCANNER.nextLine();
		return opcion;
	}

	public static void limpiarScanner() {
		SCANNER.nextLine();
	}

	public static String procesarOpcion(int opcion) {
		
		String respuesta = "";
		int numeroCuenta;
		String numeroBolsillo = "";
		String cadenaCuenta = "";
		String numero="";
		Double monto;
		int opcionConsulta;
		
		
		switch (opcion) {

//		case 0:
//			respuesta = "CARGA" + "," + "src/resources/historialTransacciones.txt";
//			break;

		case 1:
			
			System.out.println("Por favor ingrese su nombre completo: ");
			String nombre = SCANNER.nextLine();

			respuesta = "CREAR_CUENTA" + "," + nombre;
			break;
			
		case 2:
			
			System.out.println("Ingrese el numero de cuenta al que le va depositar: ");
			numeroCuenta = SCANNER.nextInt();

			System.out.println("Ingrese el monto a depositar: ");
			monto = SCANNER.nextDouble();

			respuesta = "DEPOSITAR" + "," + numeroCuenta + "," + monto;
			System.out.println(respuesta);
			break;
			
		case 3:
			
			System.out.println("Ingrese el numero de cuenta del que va a retirar: ");
			numeroCuenta = SCANNER.nextInt();
			
			System.out.println("Ingrese el monto a retirar: ");
			monto = SCANNER.nextDouble();
			
			respuesta = "RETIRAR" + "," + numeroCuenta + "," + monto;
			break;
			
		case 4:
			
			System.out.println("Ingrese el numero de cuenta que va a cancelar: ");
			numeroCuenta = SCANNER.nextInt();
			
			respuesta = "CANCELAR" + "," + numeroCuenta;
			break;
			
		case 5:
			System.out.println("Ingrese el numero de cuenta al consultar el saldo: ");
			numero = SCANNER.nextLine();
			
			respuesta = "CONSULTAR_SALDO" + "," + numero;

			break;
		case 6:
			System.out.println("Saliendo...");
			respuesta = "SALIR" + "," + "adios";
			break;
			
		case 7:
			//System.out.println("I");
			respuesta="CONSULTAR_CUENTAS";
			break;
		default:
			System.out.println("Por favor ingrese una opcion entre 1 y 9");
			break;
		}

		return respuesta;
	}


	public static void protocol(Socket socket) throws Exception {
		int opcion = 0;

		while (opcion != 9) {
			createStreams(socket);
			menuOpciones();
			opcion = leerOpcion();
			String fromUser = procesarOpcion(opcion);

			toNetwork.println(fromUser);

			String fromServer = fromNetwork.readLine();
			System.out.println("\n\n[Client] From server:" + fromServer);
			System.out.println("***********************************************" + "\n\n");

		}

	}

	private static void createStreams(Socket socket) throws IOException {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
