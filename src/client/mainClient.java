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
				
				case 1:
					try {
						System.out.println("Ingrese su nombre: ");
						String nombre = sn.nextLine();

						nombre.trim();
						fromUser = "CREAR_CUENTA" + "," + nombre;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();
						
						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);
						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 2:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();
						
						System.out.println("Ingrese el tipo de apuesta: ");
						String tipo = sn.nextLine();
						
						System.out.println("Ingrese el numero de la apuesta: ");
						String numApuesta = sn.nextLine();

						fromUser = "APOSTAR" + "," + numCuenta + "," + tipo + "," +numApuesta;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

				case 3:
					
					String respuestaPregunta="";
					String fromUser6="";
					String fromServer6="";

					try {
						
						fromUser = "CERRAR";
						EchoTCPClientProtocol.toNetwork.println(fromUser);
						
						fromServer =EchoTCPClientProtocol.fromNetwork2.readLine();
						
						if(fromServer.contains("¿Está seguro de cerrar las apuestas?")) {
							
							System.out.println(fromServer);
							System.out.println("Ingrese SI o NO: ");
							respuestaPregunta = sn.nextLine();
							
							fromUser6 = respuestaPregunta;
							EchoTCPClientProtocol.toNetwork2.println(fromUser6);
							
							System.out.println();

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

				case 4:

					String reporte="REPORTE DE APUESTAS \n";
					String aux="";
					
					try {

						fromUser = "REPORTAR";
						EchoTCPClientProtocol.toNetwork.println(fromUser);
						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						aux= fromServer;
						
						String reportes[] = aux.split("-");
						
						
						for(int i=0;i<reportes.length;i++) {
							
							reporte += reportes[i] +"\n";
						}
						
						
						System.out.println(reporte);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
					
				case 5:

					try {
						
						System.out.println("Ingrese el numero a sortear: ");
						int numGanador = sn.nextInt();
						sn.nextLine();

						fromUser = "SORTEO" + "," + numGanador;
						
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println( fromServer);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
					
				case 6:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuentaA = sn.nextInt();
						sn.nextLine();

						fromUser = "CANCELAR" + "," + numCuentaA;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 7:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuenta = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a depositar: ");
						double valor = sn.nextDouble();

						fromUser = "DEPOSITAR" + "," + numCuenta + "," + valor;
						EchoTCPClientProtocol.toNetwork.println(fromUser);

						System.out.println();

						fromServer = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
					
				case 8:

					try {
						System.out.println("Ingrese el numero de la cuenta: ");
						int numCuentaR = sn.nextInt();
						sn.nextLine();

						System.out.println("Ingrese el valor a retirar: ");
						double valorR = sn.nextDouble();

						String fromUser2 = "RETIRAR" + "," + numCuentaR + "," + valorR;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						System.out.println(fromServer2);

						reiniciarConexion();

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
					
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