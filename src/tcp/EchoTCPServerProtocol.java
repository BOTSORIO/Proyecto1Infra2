package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


import model.*;


public class EchoTCPServerProtocol {
	private static PrintWriter toNetwork;
	private static BufferedReader fromNetwork;
	private static String message = "";
	private static HashMap<Usuario, Cuenta> tablaUsuarios = new HashMap<>();
	private static final String RUTALOG = "src/resources/historialTransacciones.txt";

	public static void protocol(Socket socket) throws IOException {

		// -----------Variables-----------//
		String[] mensajeDividio;
		do {
			createStreams(socket);
			message = fromNetwork.readLine();
			String answer = "";
			System.out.println(message);
			mensajeDividio = message.split(",");

			answer = procesarSolicitud(mensajeDividio);

			if(message.contains("CARGA")) {
				
				toNetwork.println("Datos cargados correctamente");
			}
			else {
				
				toNetwork.print(answer);
			}
			
			
		} while (!mensajeDividio[0].equalsIgnoreCase("SALIR"));

	}

	public static String procesarSolicitud(String[] message) {
		
		String answer = "";
		int numeroCuenta;
		String numeroApuesta = "";
		String cadenaConsulta = "";
		double saldo;
		
		switch (message[0]) {

//		case "CARGA":
//			String rutaArchivo = message[1];
//			cargarMensajes(rutaArchivo);
//			break;

		case "CREAR_CUENTA":
			answer = crearCuenta(message[1]);
			return answer;

		case "DEPOSITAR":
			
			numeroCuenta = Integer.parseInt(message[1]);
			saldo = Double.parseDouble(message[2]);
			return answer = depositarACuenta(numeroCuenta, saldo);

		case "RETIRAR":
			numeroCuenta = Integer.parseInt(message[1]);
			saldo = Double.parseDouble(message[2]);
			return answer = retirarDeCuenta(numeroCuenta, saldo);

		case "CANCELAR":
			numeroCuenta = Integer.parseInt(message[1]);
			return answer = cancelarCuenta(numeroCuenta);

		case "CONSULTAR_SALDO":
			cadenaConsulta = message[1];
			return answer = consultarSaldo(cadenaConsulta);
			
		case "SALIR":
			return answer = "Vuelve pronto";

		default:
			break;
		}

		return "";
	}

	public static String crearCuenta(String nombre) {

		Usuario usuario = new Usuario(nombre);
		
		if (tablaUsuarios.containsKey(usuario)) {
			return "El nombre de usuario ya existe";
		}
		
		int numeroCuenta = tablaUsuarios.size()+1;
		
		Cuenta cuenta = new Cuenta(numeroCuenta, nombre);
		usuario.setCuenta(cuenta);
		tablaUsuarios.put(usuario, cuenta);

//		if (message.contains("CARGA")) {
//
//			System.out.println("Transaccion cargada correctamente: CREAR_CUENTA,"+nombre);
//			
//		} else {
//
//			ArchivoUtil.guardarRegistroLog(message, RUTALOG);
//		}

		return "Transaccion Exitosa, su numero de cuenta es: " + cuenta.getNumeroCuenta();
	}

	public static String depositarACuenta(int numeroCuenta, double saldo) {
		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : tablaUsuarios.values()) {
			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				saldoCuenta = cuenta.getSaldo() + saldo;
				cuenta.setSaldo(saldoCuenta);

//				if (message.contains("CARGA")) {
//
//					System.out.println("Transaccion cargada correctamente: DEPOSITAR,"+numeroCuenta+","+saldo);
//					
//				} else {
//
//					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
//				}

				return "Deposito Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}

	
	public static String retirarDeCuenta(int numeroCuenta, double saldo) {

		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : tablaUsuarios.values()) {
			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (saldo > cuenta.getSaldo()) {
					return "Fondos insuficientes";
				}
				saldoCuenta = cuenta.getSaldo() - saldo;
				cuenta.setSaldo(saldoCuenta);

//				if (message.contains("CARGA")) {
//					
//					System.out.println("Transaccion cargada correctamente: RETIRAR,"+numeroCuenta+","+saldo);
//
//				} else {
//
//					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
//				}

				return "Retiro Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}

	public static String cancelarCuenta(int numeroCuenta) {

		if (numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (Usuario u : tablaUsuarios.keySet()) {

			if (u.getCuenta().getNumeroCuenta() == numeroCuenta) {

				//validar la apuesta
				
				if (u.getCuenta().getSaldo() != 0) {

					return "No se puede eliminar la cuenta debido a que tiene saldo";
				}

				tablaUsuarios.remove(u);

//				if (message.contains("CARGA")) {
//					
//					System.out.println("Transaccion cargada correctamente: CANCELAR_CUENTA,"+numeroCuenta);
//
//				} else {
//
//					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
//				}

				return "Cuenta cancelada Exitosamente";
			}

		}

		return "La cuenta no existe";
	}

	public static String abrirBolsillo(int numeroCuenta) {

		if (numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (CuentaAhorros cuenta : tablaUsuarios.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (cuenta.getBolsillo() != null) {

					return "Ya existe un bolsillo en esta cuenta";
				}

				Bolsillo bolsillo = new Bolsillo(cuenta);
				cuenta.setBolsillo(bolsillo);

				if (message.contains("CARGA")) {
					
					System.out.println("Transaccion cargada correctamente: ABRIR_BOLSILLO,"+numeroCuenta);

				} else {

					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
				}

				return "Bolsillo creado Exitosamente, su numero de bolsillo es: " + bolsillo.getNumeroCuenta();

			}
		}

		return "La cuenta no existe";

	}

	public static String trasladarDineroABolsillo(int numeroCuenta, Double saldo) {

		double saldoCuenta = 0.0;
		double saldoBolsillo = 0.0;

		if (numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (CuentaAhorros cuenta : tablaUsuarios.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (cuenta.getBolsillo() == null) {

					return "El bolsillo no existe";
				}

				if (saldo > cuenta.getSaldo()) {
					return "Fondos insuficientes";
				}

				System.out.println("Cuenta antes: " + cuenta.getSaldo());
				saldoCuenta = cuenta.getSaldo() - saldo;
				cuenta.setSaldo(saldoCuenta);
				System.out.println("Cuenta despues: " + cuenta.getSaldo());
				System.out.println("Bolsillo antes: " + cuenta.getBolsillo().getSaldo());
				saldoBolsillo = cuenta.getBolsillo().getSaldo() + saldo;
				cuenta.getBolsillo().setSaldo(saldoBolsillo);
				System.out.println("Bolsillo despues: " + cuenta.getBolsillo().getSaldo());

				if (message.contains("CARGA")) {
					
					System.out.println("Transaccion cargada correctamente: TRASLADAR,"+numeroCuenta+","+saldo);

				} else {

					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
				}

				return "Traslado Exitoso, su nuevo saldo en el bolsillo es de: " + "$"
						+ cuenta.getBolsillo().getSaldo();

			}
		}

		return "La cuenta no existe";

	}

	public static String cancelarBolsillo(String numeroBolsillo) {
		if (!numeroBolsillo.contains("b") || numeroBolsillo == null) {
			return "Informacion Incompleta";
		}
		if (!numeroBolsillo.endsWith("b")) {
			return "Informacion incosistente";
		}
		String cadenaCuenta = numeroBolsillo.substring(0, numeroBolsillo.lastIndexOf('b'));
		int numeroCuenta = Integer.parseInt(cadenaCuenta);
		double saldoCuenta = 0.0;

		if (numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (CuentaAhorros cuenta : tablaUsuarios.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (cuenta.getBolsillo() == null) {

					return "El bolsillo no existe";
				}

				saldoCuenta = cuenta.getSaldo() + cuenta.getBolsillo().getSaldo();
				cuenta.setSaldo(saldoCuenta);
				cuenta.setBolsillo(null);

				System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

				if (message.contains("CARGA")) {
					
					System.out.println("Transaccion cargada correctamente: CANCELAR_BOLSILLO,"+numeroBolsillo);

				} else {

					ArchivoUtil.guardarRegistroLog(message, RUTALOG);
				}

				return "Bolsillo cancelado Exitosamente, su nuevo saldo en la cuenta es de: " + "$" + cuenta.getSaldo();

			}

		}

		return "La cuenta no existe";

	}

	public static String consultarSaldo(String cadenaConsulta) {

		int numeroCuenta;
		double saldoCuenta = 0.0;
		String cadenaCuenta = "";

		if (cadenaConsulta.equals(null)) {
			return "Informacion incompleta";
		}

		if (esNumero(cadenaConsulta)) {

			numeroCuenta = Integer.parseInt(cadenaConsulta);
			for (Cuenta cuenta : tablaUsuarios.values()) {

				if (cuenta.getNumeroCuenta() == numeroCuenta) {

//			if (message.contains("CARGA")) {
//								
//			System.out.println("Transaccion cargada correctamente: CONSULTAR,"+numeroCuenta);
//			} else {
//
//				ArchivoUtil.guardarRegistroLog(message, RUTALOG);
//			}

					return "El saldo de la cuenta es: " + "$" + cuenta.getSaldo();

				}

			}
			return "La cuenta no existe";

		} else {
			return "informacion Inconsistente";
		}

	}
	

	public static boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}

	}

	private static void createStreams(Socket socket) throws IOException {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

//	public static void cargarMensajes(String rutaArchivo) {
//
//		ArrayList<String> mensajes = ArchivoUtil.cargarTransacciones(rutaArchivo);
//
//		for (int i = 0; i < mensajes.size(); i++) {
//
//			if (mensajes.get(i) != null) {
//
//				String[] mensajeDividido;
//
//				mensajeDividido = mensajes.get(i).split(",");
//				procesarSolicitud(mensajeDividido);
//			}
//
//		}
//
//	}

}
