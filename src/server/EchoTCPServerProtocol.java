package server;

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
	
	public static PrintWriter toNetwork2;

	public static BufferedReader fromNetwork2;

	private static HashMap<Usuario, Cuenta> cuentas = new HashMap<>();

	public static Usuario usuarioInicio = new Usuario("Casa");
	public static Cuenta cuentaInicio = new Cuenta(0, usuarioInicio.getNombre());

	private static HashMap<Apuesta, Cuenta> apuestasCasa = new HashMap<>();

	private static Boolean abiertaCerrado= false;
	
	private static ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
	

	public static void protocol(Socket socket) throws IOException {
		createStreams(socket);
		mainServer.leerOpcionCliente();

	}

	private static void createStreams(Socket socket) throws IOException {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		toNetwork2 = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
		
		Transaccion transaccion = new Transaccion(cuenta, "CREAR_CUENTA", cuenta.getNumeroCuenta());
		transacciones.add(transaccion);
		

		return "Transaccion Exitosa, su numero de cuenta es: " + cuenta.getNumeroCuenta();

	}

	
	public static String apostar(int numeroCuenta, String tipo,String numeroApuesta) {
		
		Apuesta apuesta=new Apuesta(); 
		int numApuesta=0;
		String num="";
		double saldoCuenta = 0.0;
		double saldoCasa = 0.0;
		
		char n[] = numeroApuesta.toCharArray();
		
		if(abiertaCerrado==false) {
			
			for (Cuenta cuenta : cuentas.values()) {

				if (cuenta.getNumeroCuenta() == numeroCuenta) {

					if(cuenta.getSaldo()>=10000){
						
						switch (tipo) {
						
							case "A":
								
								if(n.length == 4) {
									
									numApuesta = Integer.parseInt(numeroApuesta);
									
									apuesta.setNumeroCuenta(numeroCuenta);
									apuesta.setTipo(tipo);
									apuesta.setNumeroApuesta(numApuesta);
															
									cuenta.getApuestas().add(apuesta);
									saldoCuenta = cuenta.getSaldo()-10000;
									saldoCasa = cuentaInicio.getSaldo()+ 10000;
									
									cuentaInicio.setSaldo(saldoCasa);
									cuenta.setSaldo(saldoCuenta);
									apuestasCasa.put(apuesta, cuenta);
									
									Transaccion transaccion = new Transaccion();
									transaccion.setCuenta(cuenta);
									transaccion.setServicio("APOSTAR");
									transaccion.setTipo(apuesta.getTipo());
									transaccion.setNumApuesta(apuesta.getNumeroApuesta());
									transacciones.add(transaccion);
								}
								
								break;
								
							case "B":
								
								if(n.length == 3) {
									
									for(int i=0;i<n.length;i++) {
										
										num += String.valueOf(n[i]);
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
									apuestasCasa.put(apuesta, cuenta);
									
									Transaccion transaccion = new Transaccion();
									transaccion.setCuenta(cuenta);
									transaccion.setServicio("APOSTAR");
									transaccion.setTipo(apuesta.getTipo());
									transaccion.setNumApuesta(apuesta.getNumeroApuesta());
									transacciones.add(transaccion);
								}
								
								break;
								
							case "C":
								
								if(n.length == 2) {
									
									for(int i=0;i<n.length;i++) {
										
										num += String.valueOf(n[i]);
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
									apuestasCasa.put(apuesta, cuenta);	
									
									Transaccion transaccion = new Transaccion();
									transaccion.setCuenta(cuenta);
									transaccion.setServicio("APOSTAR");
									transaccion.setTipo(apuesta.getTipo());
									transaccion.setNumApuesta(apuesta.getNumeroApuesta());
									transacciones.add(transaccion);
									
								}
								
								break;
								
						}
					}else {
						return "Saldo insuficiente";
					}
					
					return "Apuesta Exitosa";
				}
			}
			
		}else {
			return "El servicio de apuestas esta cerrado";
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
				
				Transaccion transaccion = new Transaccion(u.getCuenta(), "CANCELAR", u.getCuenta().getNumeroCuenta());
				transacciones.add(transaccion);

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
				
				Transaccion transaccion = new Transaccion(cuenta, "DEPOSITAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);

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
				
				Transaccion transaccion = new Transaccion(cuenta, "RETIRAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);

				return "Retiro Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}
	
	
	
	 public static String cerrarApuestas(){
		 
		 String confirmacion = "";
		 
	        if(!abiertaCerrado){
			    if(apuestasCasa.isEmpty()){
			    	
			       toNetwork2.println("¿Está seguro de cerrar las apuestas?");

			        try {
						confirmacion = fromNetwork2.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
			        
			        if(confirmacion.equals("SI")) {
			        	
			        	abiertaCerrado=true;
			        	
			        	return "Apuestas cerradas";
			        }else {
			        	return "Proceso cancelado";
			        }

			    }else {
			    	
			    	abiertaCerrado=true;
		    
		        	return "Apuestas cerradas";
			    }
	        }
	        
	        return "Las apuestas ya estan cerradas";
	    }

	
	public static String consultar(int numCuenta) {
		
		for (Cuenta cuenta : cuentas.values()) {

			if (cuenta.getNumeroCuenta() == numCuenta) {

				double saldoCuenta = cuenta.getSaldo();
				
				Transaccion transaccion = new Transaccion(cuenta, "CONSULTAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);

				return "El saldo de la cuenta es de: " + saldoCuenta;
				
			}
		}

		return "La cuenta no existe";
	}
	
	
	public static String reporteApuestas() {
		
		String reporte="";
		int cantidadA=0;
		int cantidadB=0;
		int cantidadC=0;
		int i=0;
		
		for(Apuesta apuesta : apuestasCasa.keySet()) {
			
			if(apuesta.getTipo().equals("A")) {
				cantidadA++;
			}else {
				if(apuesta.getTipo().equals("B")) {
					cantidadB++;
				}else {
					cantidadC++;
				}
			}
		}
		
		
		Transaccion transaccion = new Transaccion("REPORTAR");
		transacciones.add(transaccion);
		
		if(!apuestasCasa.isEmpty()) {
			
			for(Cuenta cuenta : apuestasCasa.values()) {
				
				reporte += cuenta.getUsuario() + ","+ cuenta.getApuestas().get(i).getTipo()+"," + cuenta.getApuestas().get(i).getNumeroApuesta()+"-";
			}
			
			reporte += "Apuestas tipo A: " + Integer.toString(cantidadA) +"-" + "Apuestas tipo B: " + Integer.toString(cantidadB) +"-" + "Apuestas tipo C: " + Integer.toString(cantidadC);
		}else {
			return "No hay apuestas registradas";
		}
		
		return reporte;
		
	}
	
	
//	public static String sortearApuestas(int numeroGanador) {
//		
//		double recaudoA=0.0;
//		double recaudoB=0.0;
//		double recaudoC=0.0;
//		double pagar = 0.0;
//		double total=0.0;
//		double totalM =0.0;
//		char ganador[];
//		boolean verificado= false;
//		String num = null;
//
//		for(Apuesta apuesta : apuestasCasa.keySet()) {
//			
//			if(apuesta.getTipo().equals("A")) {
//				recaudoA = recaudoA +10000;
//			}else {
//				if(apuesta.getTipo().equals("B")) {
//					recaudoB = recaudoB + 10000;
//				}else {
//					recaudoC = recaudoC + 10000;
//				}
//			}
//		}
//		
//		for(Cuenta cuenta : apuestasCasa.values()) {
//			
//			for(Apuesta apuesta : apuestasCasa.keySet()) {
//				
//				if(cuenta.getNumeroCuenta() == apuesta.getNumeroCuenta()) {
//					
//					char n[] = String.valueOf(apuesta.getNumeroApuesta()).toCharArray();
//					
//					if(n.length==4) {
//						
//						if(apuesta.getNumeroApuesta() == numeroGanador) {
//							
//							pagar = (recaudoA * 0.8)+(recaudoB*0.7)+(recaudoC*0.6);
//							
//							total = cuenta.getSaldo() + pagar;
//							totalM = cuentaInicio.getSaldo()-pagar;
//							
//							cuenta.setSaldo(total);
//							cuentaInicio.setSaldo(totalM);
//							
//							apuestasCasa.remove(apuesta);
//							
//							return "Felicitaciones por ganar " + cuenta.getUsuario();
//							
//						}
//					}else {
//						if(n.length==3) {
//							
//							ganador = String.valueOf(numeroGanador).toCharArray();
//							
//							for(int i=1;i<ganador.length;i++) {
//								
//								gans = null;
//								gans[i-1] = ganador[i];
//								
//								for(int j=0;j<gans.length;i++) {
//									
//									num += String.valueOf(gans[i]);
//								}
//								
//								int numV= Integer.parseInt(num);
//								
//								if(numV == apuesta.getNumeroApuesta()) {
//									
//									pagar = (recaudoA * 0.8)+(recaudoB*0.7)+(recaudoC*0.6);
//									
//									total = cuenta.getSaldo() + pagar;
//									totalM = cuentaInicio.getSaldo()-pagar;
//									
//									cuenta.setSaldo(total);
//									cuentaInicio.setSaldo(totalM);
//									
//									apuestasCasa.remove(apuesta);
//									
//									return "Felicitaciones por ganar " + cuenta.getUsuario();
//								}
//							}	
//						}else {
//							
//							ganador = String.valueOf(numeroGanador).toCharArray();
//							
//							for(int i=2;i<ganador.length;i++) {
//								
//								if(ganador[i] == n[i-1]) {
//									
//									verificado=true;
//								}
//								
//								if(verificado) {
//									pagar = (recaudoA * 0.8)+(recaudoB*0.7)+(recaudoC*0.6);
//									
//									total = cuenta.getSaldo() + pagar;
//									totalM = cuentaInicio.getSaldo()-pagar;
//									
//									cuenta.setSaldo(total);
//									cuentaInicio.setSaldo(totalM);
//									
//									apuestasCasa.remove(apuesta);
//									
//									return "Felicitaciones por ganar " + cuenta.getUsuario();
//								}
//						}
//					}
//				}
//			}
//		}
//		}
//			
//		return "No hubo ganador alguno";
//	}
	
	
	public static String sortearApuestas(int numeroGanador) {
		
		double recaudoA=0.0;
		double recaudoB=0.0;
		double recaudoC=0.0;
		double pagar = 0.0;
		double total=0.0;
		double totalVarios=0.0;
		double totalM =0.0;
		int cantidadGanadores=0;
		ArrayList<Cuenta> cuentasGanadoras = new ArrayList<>();

		for(Apuesta apuesta : apuestasCasa.keySet()) {
			
			if(apuesta.getTipo().equals("A")) {
				recaudoA = recaudoA +10000;
			}else {
				if(apuesta.getTipo().equals("B")) {
					recaudoB = recaudoB + 10000;
				}else {
					recaudoC = recaudoC + 10000;
				}
			}
		}
		
		for(Cuenta cuenta : apuestasCasa.values()) {
			
			for(Apuesta apuesta : apuestasCasa.keySet()) {
				
				if(cuenta.getNumeroCuenta() == apuesta.getNumeroCuenta()) {
									
					if(apuesta.getNumeroApuesta() == numeroGanador) {
						
						cantidadGanadores++;
						cuentasGanadoras.add(cuenta);
					}
				}
			}	
		}
		
		for(Cuenta cuenta : apuestasCasa.values()) {
			
			for(Apuesta apuesta : apuestasCasa.keySet()) {
				
				if(cuenta.getNumeroCuenta() == apuesta.getNumeroCuenta()) {
									
					if(apuesta.getNumeroApuesta() == numeroGanador) {
						
						if(cantidadGanadores >0) {
							
							pagar = (recaudoA * 0.8)+(recaudoB*0.7)+(recaudoC*0.6);
							
							totalVarios = pagar / cantidadGanadores;
							
							for(int i=0; i<cuentasGanadoras.size();i++) {
								
								total=cuentasGanadoras.get(i).getSaldo()+ totalVarios;
							
								cuentasGanadoras.get(i).setSaldo(total);
							}
							
							totalM = cuentaInicio.getSaldo()-pagar;
							cuentaInicio.setSaldo(totalM);
							
							apuestasCasa.clear();
							
							Transaccion transaccion = new Transaccion("SORTEO");
							transacciones.add(transaccion);
							
							return "Felicitaciones por ganar " + cuenta.getUsuario();
							
						}else {
						
							pagar = (recaudoA * 0.8)+(recaudoB*0.7)+(recaudoC*0.6);
							
							total = cuenta.getSaldo() + pagar;
							totalM = cuentaInicio.getSaldo()-pagar;
							
							cuenta.setSaldo(total);
							cuentaInicio.setSaldo(totalM);
							
							apuestasCasa.remove(apuesta);
							
							Transaccion transaccion = new Transaccion("SORTEO");
							transacciones.add(transaccion);
							
							return "Felicitaciones por ganar " + cuenta.getUsuario();
						}
					}
				}
			}	
		}
		
		Transaccion transaccion = new Transaccion("SORTEO");
		transacciones.add(transaccion);
			
		return "No hubo ganador alguno";
	}
	
	
	public static String mostrarTransacciones() {
		String cadena = "";
		Transaccion transaccion;
		Cuenta cuenta;

		if (transacciones.size() > 0) {
			for (int i = 0; i < transacciones.size(); i++) {
				transaccion = transacciones.get(i);

				if (transaccion.getCuenta() != null) {
					cuenta = transaccion.getCuenta();

					cadena += "Cuenta: " + transaccion.getNumCuenta() + "," + cuenta.getUsuario() + ","
							+ cuenta.getSaldo() + "\n";


					cadena += transaccion.getServicio()+ "\n";
				}
			}
		}
		return cadena;
	}
	
}