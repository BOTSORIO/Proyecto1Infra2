package client;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import server.EchoTCPServerProtocol;

public class mainClient {

	private static EchoTCPClient ec;
	private static boolean salir = false;

	public static void main(String[] args) {
		ec = new EchoTCPClient();

		try {
			ec.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mostrarMenu() throws IOException {
		
		Scanner sn = new Scanner(System.in);
		String fromServer="";
		String fromUser="";

		int opcion; 

		while (!salir) {
			System.out.println();
			System.out.println("Casa de apuestas:");
			System.out.println("1.Crear una cuenta");
			System.out.println("2.Apostar");
			System.out.println("3.Cerrar apuestas");
			System.out.println("4.Reporte de apuestas");
			System.out.println("5.Sortear");
			System.out.println("6.Cancelar cuenta");
			System.out.println("7.Depositar dinero en una cuenta");
			System.out.println("8.Retirar dinero en una cuenta");
			System.out.println("9.Consultar saldo");
			System.out.println("10.Cargar datos");	
			System.out.println("11.Salir");

			try {

				System.out.println("Escribe una de las opciones");
				opcion = sn.nextInt();
				sn.nextLine();

				switch (opcion) {
				
				//Crear una cuenta.
				case 1:
					try {
						System.out.println("Ingrese su nombre: ");
						String nombre = sn.nextLine();
						
						//.trim limpia el nombre ingresado, quitando los espacios del inicio y del final.
						nombre.trim();
						//Se establece la cadena de texto que posteriormente recibira el servidor.
						fromUser = "CREAR_CUENTA" + "," + nombre;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						
						//Se lee e imprime la respuesta que haya enviado el servidor al cliente.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);
						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				//Apostar
				case 2:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();
						
						System.out.println("Ingrese el tipo de apuesta: ");
						String tipo = sn.nextLine();
						
						System.out.println("Ingrese el numero de la apuesta: ");
						String numApuesta = sn.nextLine();
						
						//Se establece la cadena de texto que posteriormente recibira el servidor.
						fromUser = "APOSTAR" + "," + numCuenta + "," + tipo + "," +numApuesta;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						
						//Se lee e imprime la respuesta que haya enviado el servidor al cliente.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				//Cerrar apuestas
				case 3:
					
					String respuestaPregunta="";
					String fromUser6="";
					String fromServer6="";

					try {
						//Se envia al servidor la instruccion de CERRAR.
						fromUser = "CERRAR";
						EchoTCPClientProtocol.toNetwork.println(fromUser);
						
						//Se recibe la respuesta del servidor.
						fromServer =EchoTCPClientProtocol.fromNetwork2.readLine();
						
						/*Se compara la respuesta del servidor, si entra a este if 
						 * significa que no se han realizado apuestas hasta el momento. 
						 * por ello se requiere la confirmacion de cerrarlas.*/
						if(fromServer.contains("¿Está seguro de cerrar las apuestas?")) {
							
							System.out.println(fromServer);
							System.out.println("Ingrese SI o NO: ");
							respuestaPregunta = sn.nextLine();
							
							//Se envia la respuesta a la confirmacion
							fromUser6 = respuestaPregunta;
							EchoTCPClientProtocol.toNetwork2.println(fromUser6);
							
							System.out.println();
							//Se recibe la respuesta del servidor.
							fromServer6 = EchoTCPClientProtocol.fromNetwork.readLine();
							System.out.println(fromServer6);
							
							reiniciarConexion();
						
						}else {
							System.out.println(fromServer);
							reiniciarConexion();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				
				//Reporte de apuestas
				case 4:

					String reporte="REPORTE DE APUESTAS \n";
					String aux="";
					
					try {
						fromUser = "REPORTAR";
						//Se envia al servidor la instruccion REPORTAR.
						EchoTCPClientProtocol.toNetwork.println(fromUser);
						System.out.println();
						
						//Se recibe la respuesta del servidor.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						aux= fromServer;
						
						String reportes[] = aux.split("-");
						
						//Se modifica la respuesta del servidor, para acomodarla visualmente.
						for(int i=0;i<reportes.length;i++) {
							
							reporte += reportes[i] +"\n";
						}
						
						//Se imprime el reporte de las apuestas.
						System.out.println(reporte);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
					
				//Sortear
				case 5:

					try {
						
						System.out.println("Ingrese el numero a sortear: ");
						int numGanador = sn.nextInt();
						sn.nextLine();
						//Se establece la instruccion que se enviara al servidor.
						fromUser = "SORTEO" + "," + numGanador;
						//Se envia al servidor.
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						//Se lee la respuesta del servidor.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println( fromServer);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
					
				//Cancelar cuenta
				case 6:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuentaA = sn.nextInt();
						sn.nextLine();
						
						//Se establece y envia la instruccion al servidor
						fromUser = "CANCELAR" + "," + numCuentaA;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						//Se lee la respuesta del servidor.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
					
				//Depositar dinero en una cuenta
				case 7:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a depositar: ");
						double valor = sn.nextDouble();
						
						//Se establece y envia la instruccion al servidor.
						fromUser = "DEPOSITAR" + "," + numCuenta + "," + valor;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						
						//Se lee la respuesta del servidor.
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
					
				//Retirar dinero en una cuenta
				case 8:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuentaR = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a retirar: ");
						double valorR = sn.nextDouble();

						//Se establece y envia una instruccion al servidor.
						String fromUser2 = "RETIRAR" + "," + numCuentaR + "," + valorR;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();
						
						//Se lee la respuesta del servidor.
						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
					
				//Consultar saldo
				case 9:

					try {
						System.out.println("Ingrese el numero de cuenta: ");
						String cuenta = sn.nextLine();

						fromUser = "CONSULTAR_SALDO" + "," + cuenta;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					break;

				//Cargar datos
				case 10:

					try {
						System.out.println("Ingrese el nombre del archivo a enviar: ");
						String nombreArchivo = sn.nextLine();
						fromUser= "CARGA" + "," + nombreArchivo;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();


						EchoTCPClient.reiniciarSocket();

						String lineas = fromServer.replace('[', ' ');
						lineas = lineas.replace(']', ' ');

						String[] lista = lineas.split("-");

						for (int i = 0; i < lista.length; i++) {

							String cadena = "";
							String cadena2 = "";
							if (i == 0) {
								cadena += lista[i].replaceAll("^\\s*", "") + "\n";
							} else {
								cadena2 = lista[i].replaceFirst(",", "");
								cadena2 = cadena2.replaceAll("^\\s*", "");
								cadena += cadena2 + "\n";
							}

							String fromUser3 = cadena;
							EchoTCPClientProtocol.toNetwork.println(fromUser3);

							String fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							System.out.println("[Client] From server: " + fromServer2);

							if (i < lista.length - 2) {

								EchoTCPClient.reiniciarSocket();
							} else {
								reiniciarConexion();
							}

						}

						reiniciarConexion();
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				case 11:

					salir = true;
					EchoTCPClientProtocol.toNetwork.println("once");

					try {
						ec.cerrarSocket();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Solo numeros entre 1 y 10");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un numero");
				sn.next();
			}
		}
	}

	/* Despues de cada instruccion enviada al servidor, se reinicia la conexion para comprobar que no se 
	 * ha seleccionado la opcion 11, que corresponde a salir.
	 */
	public static void reiniciarConexion() {
		if (!salir) {
			try {
				ec.init();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
	}
}