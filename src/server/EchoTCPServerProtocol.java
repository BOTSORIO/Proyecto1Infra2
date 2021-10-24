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

	//Inicialmente se crea el usuario y la cuenta que corresponden a la casa de apuestas, asignandole el nro 0.
	public static Usuario usuarioInicio = new Usuario("Casa");
	public static Cuenta cuentaInicio = new Cuenta(0, usuarioInicio.getNombre());

	//Se crea el HashMap que corresponde a las apuestas que se realizan en la casa.
	private static HashMap<Apuesta, Cuenta> apuestasCasa = new HashMap<>();

	private static Boolean abiertaCerrado= false;
	
	//Este arraylist es una especie de log, al final de cada accion se guarda en el lo que se hizo en el metodo.
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

		if (cuentas.size() == 0) { //Se inscribe principalmente la cuenta que corresponde a la casa de apuestas.
			usuarioInicio.setCuenta(cuentaInicio);
			cuentas.put(usuarioInicio, cuentaInicio);
		}

		Usuario usuario = new Usuario(nombre);
		
		/* Aqui se comprueba si el nombre de usuario que esta esta intentando registrar ya existe en las cuentas
		 * Haciendo la comparacion mediante un metodo equals, que utiliza como parametro el nombre de la cuenta*/
		
		if (cuentas.containsKey(usuario)) { 
			return "El nombre de usuario ya existe";
		}

		int numeroCuenta = cuentas.size();

		Cuenta cuenta = new Cuenta(numeroCuenta, nombre);
		usuario.setCuenta(cuenta);
		cuentas.put(usuario, cuenta);
		
		Transaccion transaccion = new Transaccion(cuenta, "CREAR_CUENTA", cuenta.getNumeroCuenta());
		transacciones.add(transaccion);
		
		//Respuesta que se enviara al cliente.
		return "Transaccion Exitosa, su numero de cuenta es: " + cuenta.getNumeroCuenta();

	}

	
	public static String apostar(int numeroCuenta, String tipo,String numeroApuesta) {
		
		//Se inicializan variables para gestionarlas comodamente mas adelante.
		Apuesta apuesta=new Apuesta(); 
		int numApuesta=0;
		String num="";
		double saldoCuenta = 0.0;
		double saldoCasa = 0.0;
		
		char n[] = numeroApuesta.toCharArray();
		
		if(abiertaCerrado==false) { //Se verifica que el servicio de apuesta no haya cerrado aun.
			
			for (Cuenta cuenta : cuentas.values()) {//Se recorren las cuentas registradas.

				if (cuenta.getNumeroCuenta() == numeroCuenta) {//Se compara la cuenta iterada con la ingresada para la apuesta.

					if(cuenta.getSaldo()>=10000){//Se comprueba que la cuenta tenga saldo suficiente. (min=10k)
						
						switch (tipo) {//Switch que clasifica los tipos de apuestas.
						
							case "A":
								
								if(n.length == 4) {//La apuesta tipo A: corresponde a una de 4 cifras.
									
									numApuesta = Integer.parseInt(numeroApuesta);
									
									//Se setean los valores de la apuesta.
									apuesta.setNumeroCuenta(numeroCuenta);
									apuesta.setTipo(tipo);
									apuesta.setNumeroApuesta(numApuesta);
										
									//Se añade la apuesta a la cuenta.
									cuenta.getApuestas().add(apuesta);
									
									//Se transfiere el dinero de la cuenta usuario, al de la cuenta de la casa.
									saldoCuenta = cuenta.getSaldo()-10000;
									saldoCasa = cuentaInicio.getSaldo()+ 10000;
									cuentaInicio.setSaldo(saldoCasa);
									cuenta.setSaldo(saldoCuenta);
									apuestasCasa.put(apuesta, cuenta);
									
									//"LOG"
									Transaccion transaccion = new Transaccion();
									transaccion.setCuenta(cuenta);
									transaccion.setServicio("APOSTAR");
									transaccion.setTipo(apuesta.getTipo());
									transaccion.setNumApuesta(apuesta.getNumeroApuesta());
									transacciones.add(transaccion);
								}
								
								break;
								
							case "B":
								
								if(n.length == 3) {//Apuesta tipo B: Corresponde a una de 3 cifras.
									
									for(int i=0;i<n.length;i++) {
										
										num += String.valueOf(n[i]);
									}
									
									numApuesta = Integer.parseInt(num);
									
									//se establecen los valores de la apuesta.
									apuesta.setNumeroCuenta(numeroCuenta);
									apuesta.setTipo(tipo);
									apuesta.setNumeroApuesta(numApuesta);
									
									//Se añade la apuesta a la cuenta.
									cuenta.getApuestas().add(apuesta);
									
									//Se transfiere el dinero de la cuenta usuario, al de la cuenta de la casa.
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
								
								if(n.length == 2) {//Apuesta tipo C: Corresponde a una de 2 Cifras.
									
									for(int i=0;i<n.length;i++) {
										
										num += String.valueOf(n[i]);
									}
									
									numApuesta = Integer.parseInt(num);
									
									//se establecen los valores de la apuesta.
									apuesta.setNumeroCuenta(numeroCuenta);
									apuesta.setTipo(tipo);
									apuesta.setNumeroApuesta(numApuesta);
									
									//Se añade la apuesta a la cuenta.
									cuenta.getApuestas().add(apuesta);
									
									//Se transfiere el dinero de la cuenta usuario, al de la cuenta de la casa.
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

		if (numeroCuenta <= 0) {//Se comprueba que la cuenta a eliminar es una cuenta valida y que no es la de la casa.
			return "Informacion incosistente";
		}

		for (Usuario u : cuentas.keySet()) {//Se recorren las cuentas registradas.

			if (u.getCuenta().getNumeroCuenta() == numeroCuenta) {//Se compara con la cuenta a eliminar

				if (u.getCuenta().getSaldo() != 0) {//Se comprueba el saldo de la cuenta a eliminar.

					return "No se puede eliminar la cuenta debido a que tiene saldo";
				}
				
				Transaccion transaccion = new Transaccion(u.getCuenta(), "CANCELAR", u.getCuenta().getNumeroCuenta());
				transacciones.add(transaccion);

				cuentas.remove(u);

				return "Cuenta cancelada Exitosamente";
			}
		}
		return "La cuenta no existe";
	}

	
	public static String depositar(int numeroCuenta, double saldo) {

		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {//Se comprueba que la cuenta a depositar sea una cuenta valida.
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : cuentas.values()) {//Se recorren las cuentas registradas.

			if (cuenta.getNumeroCuenta() == numeroCuenta) {//Se compara la cuenta iterada con la cuenta a depositar.

				//Se le añade el saldo a la cuenta. 
				saldoCuenta = cuenta.getSaldo() + saldo;
				cuenta.setSaldo(saldoCuenta);
				
				Transaccion transaccion = new Transaccion(cuenta, "DEPOSITAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);

				//Se envia la respuesta al cliente.
				return "Deposito Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
				
			}
		}

		return "La cuenta no existe";
	}

	
	public static String retirar(int numeroCuenta, double saldo) {

		double saldoCuenta = 0.0;

		if (saldo <= 0 || numeroCuenta < 0) {//Se comprueba que la cuenta sea valida.
			return "Informacion incosistente";
		}

		for (Cuenta cuenta : cuentas.values()) {//Se recorren las cuentas registradas, en busca de la solicitada.

			if (cuenta.getNumeroCuenta() == numeroCuenta) {

				if (saldo > cuenta.getSaldo()) {//Se comprueba que la cantidad a retirar no sea mayor al saldo.
					return "Fondos insuficientes";
				}

				//Se realiza el retiro de dinero de la cuenta.
				saldoCuenta = cuenta.getSaldo() - saldo;
				cuenta.setSaldo(saldoCuenta);
				
				Transaccion transaccion = new Transaccion(cuenta, "RETIRAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);

				//Respuesta para el cliente.
				return "Retiro Exitoso, su nuevo saldo es de: " + "$" + cuenta.getSaldo();
			}
		}

		return "La cuenta no existe";
	}
	
	
	
	 public static String cerrarApuestas(){
		 
		 String confirmacion = "";
		 
	        if(!abiertaCerrado){//Se comprueba que las apuesta no se encuentren ya cerradas.
			    if(apuestasCasa.isEmpty()){ //En caso de que no se hayan realizado apuestas hasta el momento
			    							//Ingresara para enviar como respuesta al cliente una pregunta de confirmacion.
			    							//Y se queda esperando una respuesta.
			       toNetwork2.println("¿Está seguro de cerrar las apuestas?");

			        try {
						confirmacion = fromNetwork2.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
			        
			        if(confirmacion.equals("SI")) {//Se cierran las apuestas despues de recibir la confirmacion del cliente.
			        	
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
		
		for (Cuenta cuenta : cuentas.values()) {//Se recorren las cuentas registradas.

			if (cuenta.getNumeroCuenta() == numCuenta) {//Se comprueba si la iterada es la que se esta buscando.

				double saldoCuenta = cuenta.getSaldo();
				
				Transaccion transaccion = new Transaccion(cuenta, "CONSULTAR",cuenta.getNumeroCuenta());
				transacciones.add(transaccion);
				
				//Se envia al cliente la informacion requerida.
				return "El saldo de la cuenta es de: " + saldoCuenta;
				
			}
		}
		
		return "La cuenta no existe";
	}
	
	
	public static String reporteApuestas() {
		
		//Se inicializan variables para una comoda gestion 
		String reporte="";
		double recaudoA=0.0;
		double recaudoB=0.0;
		double recaudoC=0.0;
		
		for (Apuesta apuesta : apuestasCasa.keySet()) { //Se recorren todas las apuestas realizadas, esto con
														//el fin de sumar el recaudo de cada tipo de apuesta.
			if (apuesta.getTipo().equals("A")) {
				recaudoA = recaudoA + 10000;
			} else {
				if (apuesta.getTipo().equals("B")) {
					recaudoB = recaudoB + 10000;
				} else {
					recaudoC = recaudoC + 10000;
				}
			}
		}
		
		
		Transaccion transaccion = new Transaccion("REPORTAR");
		transacciones.add(transaccion);
		
		if(!cuentas.isEmpty()) {//Entrara si se encuentra al menos una cuenta registrada
			
			for(Cuenta cuenta : cuentas.values()) {//Recorre las cuentas registradas.
						
				if(cuenta.getApuestas().size() !=0) {//Se comprueba si la cuenta iterada ha realizado alguna apuesta
				
					for(int j=0 ; j< cuenta.getApuestas().size();j++) {
						//Se guarda cada una de las apuestas de cada usuario en un string para el reporte.
						reporte += cuenta.getUsuario() + ","+ cuenta.getApuestas().get(j).getTipo()+"," + cuenta.getApuestas().get(j).getNumeroApuesta()+"-";
					}
				}
			}
			
			//Se junta la informacion de los reportes.
			reporte += "Recaudo de tipo A: " + String.valueOf(recaudoA) +"-" + "Recaudo de tipo B: " + String.valueOf(recaudoB) +"-" + "Recaudos de tipo C: " + String.valueOf(recaudoC);
		}else {
			return "No hay apuestas registradas";
		}
		
		System.out.println(reporte);
		
		//Se envia la informacion recolectada al cliente.
		return reporte;
		
	}
	
	
	public static String sortearApuestas(int numeroGanador) {
		
		//Se inicializan variables para una comoda manipulacion
		double recaudoA=0.0;
		double recaudoB=0.0;
		double recaudoC=0.0;
		double pagar = 0.0;
		double total=0.0;
		double totalVarios=0.0;
		double totalM =0.0;
		int cantidadGanadores=0;
		ArrayList<Cuenta> cuentasGanadoras = new ArrayList<>();
		
		
		if (abiertaCerrado == true) {//Se comprueba si ya se encuentran cerradas las apuestas.

			for (Apuesta apuesta : apuestasCasa.keySet()) { //Recorremos las apuestas realizadas.Para tener el 
															//recaudo de cada tipo, para al final al ganador calcular el valor ganado.
				if (apuesta.getTipo().equals("A")) {
					recaudoA = recaudoA + 10000;
				} else {
					if (apuesta.getTipo().equals("B")) {
						recaudoB = recaudoB + 10000;
					} else {
						recaudoC = recaudoC + 10000;
					}
				}
			}

			for (Cuenta cuenta : apuestasCasa.values()) {//Recorremos las cuentas registradas dentro de las apuestas realizadas

				for (Apuesta apuesta : apuestasCasa.keySet()) {//Recorremos las apuestas realizadas.

					if (cuenta.getNumeroCuenta() == apuesta.getNumeroCuenta()) {//Comparamos nroCuenta de cada cuenta con cada apuesta

						if (apuesta.getNumeroApuesta() == numeroGanador) { //Comparamos si la apuesta iterada es la ganadora

							//De ser ganadora, se aumenta la cantidad de ganadores y se agrega la cuenta a la lista de las ganadoras.
							cantidadGanadores++;
							cuentasGanadoras.add(cuenta);
						}
					}
				}
			}

			for (Cuenta cuenta : apuestasCasa.values()) {//Recorremos las cuentas registradas dentro de las apuestas realizadas

				for (Apuesta apuesta : apuestasCasa.keySet()) {//Recorremos las apuestas realizadas.

					if (cuenta.getNumeroCuenta() == apuesta.getNumeroCuenta()) {//Comparamos nroCuenta de cada cuenta con cada apuesta

						if (apuesta.getNumeroApuesta() == numeroGanador) {//Comparamos si la apuesta iterada es la ganadora

							//Entrara si hay multiples ganadores.
							if (cantidadGanadores > 0) {
								
								//Se calcula la cantidad a pagar. 
								pagar = (recaudoA * 0.8) + (recaudoB * 0.7) + (recaudoC * 0.6);

								//Se divide el premio entre el total de ganadores.
								totalVarios = pagar / cantidadGanadores;

								//Se recorren todas las cuentas ganadoras y se les paga su parte del premio.
								for (int i = 0; i < cuentasGanadoras.size(); i++) {

									total = cuentasGanadoras.get(i).getSaldo() + totalVarios;

									cuentasGanadoras.get(i).setSaldo(total);
								}

								//Se retira el dinero pagado por el premio a la casa.
								totalM = cuentaInicio.getSaldo() - pagar;
								cuentaInicio.setSaldo(totalM);

								//Se limpia la lista de apuestas.
								apuestasCasa.clear();

								Transaccion transaccion = new Transaccion("SORTEO");
								transacciones.add(transaccion);
								
								//Se envia al cliente la respuesta (Ocurrira mas de una vez, una por ganador)
								return "Felicitaciones por ganar " + cuenta.getUsuario();

							} else { //Aqui entra si hay un ganador unico
								
								//Se calcula el valor total del premio
								pagar = (recaudoA * 0.8) + (recaudoB * 0.7) + (recaudoC * 0.6);

								//Se añade el premio a la cuenta ganadora.
								total = cuenta.getSaldo() + pagar;
								//Se retira el valor del premio a la cuenta de la casa.
								totalM = cuentaInicio.getSaldo() - pagar;

								cuenta.setSaldo(total);
								cuentaInicio.setSaldo(totalM);

								//Se limpia las apuestas de la casa.
								apuestasCasa.remove(apuesta);

								Transaccion transaccion = new Transaccion("SORTEO");
								transacciones.add(transaccion);

								return "Felicitaciones por ganar " + cuenta.getUsuario();
							}
						}
					}
				}
			}

		} else {
			return "Primero se deben cerrar las apuestas";
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