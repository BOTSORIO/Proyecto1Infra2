package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;

/**
 * The Class EchoTCPServerProtocol.
 */
public class EchoTCPServerProtocol {

	public static PrintWriter toNetwork;

	public static BufferedReader fromNetwork;

	private static HashMap<Usuario, Cuenta> cuentas = new HashMap<>();

	public static Usuario usuarioInicio = new Usuario("Casa");
	public static Cuenta cuentaInicio = new Cuenta(0, usuarioInicio.getNombre());

	private static ArrayList<Apuesta> apuestasCasa = new ArrayList<Apuesta>();

	public static void protocol(Socket socket) throws IOException {
		createStreams(socket);
		mainServer.leerOpcionCliente();
	}

	private static void createStreams(Socket socket) throws IOException {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public static String abrirCuenta(String nombre) {

		if (cuentas.size() == 0) {
			usuarioInicio.setCuenta(cuentaInicio);
			cuentas.put(usuarioInicio, cuentaInicio);
		}

		Usuario usuario = new Usuario(nombre);

		if (cuentas.containsKey(usuario)) {
			return "El nombre de usuario ya existe";
		}

		int numeroCuenta = cuentas.size();

		Cuenta cuenta = new Cuenta(numeroCuenta, nombre);
		usuario.setCuenta(cuenta);
		cuentas.put(usuario, cuenta);

		return "Transaccion Exitosa, su numero de cuenta es: " + cuenta.getNumeroCuenta();

	}

	
	public static String apostar(int numeroCuenta, String tipo,String numeroApuesta) {
		
		Apuesta apuesta=new Apuesta(); 
		int numApuesta=0;
		String num="";
		double saldoCuenta = 0.0;
		double saldoCasa = 0.0;
		
		char n[] = numeroApuesta.toCharArray();
		int a[]= new int[4];	
		
		for(int i=0; i<numeroApuesta.length();i++) {
			
			a[i]= Integer.parseInt(String.valueOf(n[i]));
		}
		
		
		for (Cuenta cuenta : cuentas.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if(cuenta.getSaldo()>=10000){
					
					switch (tipo) {
					
						case "A":
							
							numApuesta = Integer.parseInt(numeroApuesta);
							
							apuesta.setNumeroCuenta(numeroCuenta);
							apuesta.setTipo(tipo);
							apuesta.setNumeroApuesta(numApuesta);
													
							cuenta.getApuestas().add(apuesta);
							saldoCuenta = cuenta.getSaldo()-10000;
							saldoCasa = cuentaInicio.getSaldo()+ 10000;
							
							cuentaInicio.setSaldo(saldoCasa);
							cuenta.setSaldo(saldoCuenta);
							apuestasCasa.add(apuesta);
							break;
							
						case "B":
							
							for(int i=1;i<a.length;i++) {
								
								num += a[i];
							}
							
							numApuesta = Integer.parseInt(num);
							
							apuesta.setNumeroCuenta(numeroCuenta);
							apuesta.setTipo(tipo);
							apuesta.setNumeroApuesta(numApuesta);
							
							cuenta.getApuestas().add(apuesta);
							
							saldoCuenta = cuenta.getSaldo()-10000;
							saldoCasa = cuentaInicio.getSaldo()+ 10000;
							
							cuentaInicio.setSaldo(saldoCasa);
							cuenta.setSaldo(saldoCuenta);
							apuestasCasa.add(apuesta);	
							break;
							
						case "C":
							
							for(int i=2;i<a.length;i++) {
								
								num += a[i];
							}
							
							numApuesta = Integer.parseInt(num);
							
							apuesta.setNumeroCuenta(numeroCuenta);
							apuesta.setTipo(tipo);
							apuesta.setNumeroApuesta(numApuesta);
							
							cuenta.getApuestas().add(apuesta);
							saldoCuenta = cuenta.getSaldo()-10000;
							saldoCasa = cuentaInicio.getSaldo()+ 10000;
							
							cuentaInicio.setSaldo(saldoCasa);
							cuenta.setSaldo(saldoCuenta);
							apuestasCasa.add(apuesta);	
							break;
							
					}
				}else {
					return "Saldo insuficiente";
				}
				return "Apuesta Exitosa";
			}
		}
			
		return "La cuenta no existe";
	}

	public static String cancelarCuenta(int numeroCuenta) {

		if (numeroCuenta <= 0) {
			return "Informacion incosistente";
		}

		for (Usuario u : cuentas.keySet()) {

			if (u.getCuenta().getNumeroCuenta() == numeroCuenta) {

				if (u.getCuenta().getSaldo() != 0) {

					return "No se puede eliminar la cuenta debido a que tiene saldo";
				}

				cuentas.remove(u);

				return "Cuenta cancelada Exitosamente";
			}
		}
		return "";
	}

	
	public static String depositar(int numeroCuenta, double saldo) {

		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : cuentas.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				saldoCuenta = cuenta.getSaldo() + saldo;
				cuenta.setSaldo(saldoCuenta);

				return "Deposito Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}

	public static String retirar(int numeroCuenta, double saldo) {

		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : cuentas.values()) {

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (saldo > cuenta.getSaldo()) {
					return "Fondos insuficientes";
				}

				saldoCuenta = cuenta.getSaldo() - saldo;
				cuenta.setSaldo(saldoCuenta);

				System.out.println(cuentas.toString());

				return "Retiro Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}
	
	
	public static String consultar(int numCuenta) {
		
		for (Cuenta cuenta : cuentas.values()) {

			if (cuenta.getNumeroCuenta() == numCuenta) {

				double saldoCuenta = cuenta.getSaldo();

				return "El saldo de la cuenta es de: " + saldoCuenta;

			}
		}

		return "La cuenta no existe";
		
	}

}