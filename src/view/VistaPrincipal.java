package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import client.*;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VistaPrincipal {

	private JFrame frmProyecto;


	public static final int PORT = 3400;
	public static final String SERVER = "localhost";
	private Socket clientSideSocket;


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VistaPrincipal window = new VistaPrincipal();
					window.frmProyecto.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Image imagenFondo;
	public URL fondo;

	/**
	 * Create the application.
	 */
	public VistaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		fondo = this.getClass().getResource("/img/fondo.jpeg");
		imagenFondo = new ImageIcon(fondo).getImage();
		
		frmProyecto = new JFrame();
		frmProyecto.setFont(new Font("Dialog", Font.ITALIC, 12));
		frmProyecto.setTitle("Proyecto 1 - Infraestructura de comunicaciones");
		frmProyecto.setBounds(100, 100, 540, 565);
		frmProyecto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProyecto.getContentPane().setLayout(null);
		
		JPanel panel_Inicio = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel_Inicio.setBackground(Color.WHITE);
		panel_Inicio.setBounds(0, 0, 524, 526);
		frmProyecto.getContentPane().add(panel_Inicio);
		panel_Inicio.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bienvenidos a MegaApuestas, por favor seleccione una opci\u00F3n:");
		lblNewLabel_1_1.setBounds(49, 62, 464, 40);
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_Inicio.add(lblNewLabel_1_1);
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.setForeground(Color.BLACK);
		btnCrearCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearCuenta.setBackground(Color.LIGHT_GRAY);
		btnCrearCuenta.setBounds(146, 119, 214, 23);
		panel_Inicio.add(btnCrearCuenta);
		
		JButton btnApostar = new JButton("Apostar");
		btnApostar.setForeground(Color.BLACK);
		btnApostar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnApostar.setBackground(Color.LIGHT_GRAY);
		btnApostar.setBounds(146, 159, 214, 23);
		panel_Inicio.add(btnApostar);
		
		JButton btnNewButton_2 = new JButton("Cerrar apuestas");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(146, 199, 214, 23);
		panel_Inicio.add(btnNewButton_2);
		btnNewButton_2.setForeground(Color.BLACK);
		
		JButton btnReporteApuestas = new JButton("Reporte de apuestas");
		btnReporteApuestas.setForeground(Color.BLACK);
		btnReporteApuestas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReporteApuestas.setBackground(Color.LIGHT_GRAY);
		btnReporteApuestas.setBounds(146, 239, 214, 23);
		panel_Inicio.add(btnReporteApuestas);
		
		JButton btnSortear = new JButton("Sortear");
		btnSortear.setForeground(Color.BLACK);
		btnSortear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortear.setBackground(Color.LIGHT_GRAY);
		btnSortear.setBounds(146, 279, 214, 23);
		panel_Inicio.add(btnSortear);
		
		JButton btnCancelarCuenta = new JButton("Cancelar cuenta");
		btnCancelarCuenta.setForeground(Color.BLACK);
		btnCancelarCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelarCuenta.setBackground(Color.LIGHT_GRAY);
		btnCancelarCuenta.setBounds(146, 319, 214, 23);
		panel_Inicio.add(btnCancelarCuenta);
		
		JButton btnDepositar = new JButton("Depositar dinero en una cuenta");
		btnDepositar.setForeground(Color.BLACK);
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDepositar.setBackground(Color.LIGHT_GRAY);
		btnDepositar.setBounds(146, 359, 214, 23);
		panel_Inicio.add(btnDepositar);
		
		JButton btnRetirar = new JButton("Retirar dinero en una cuenta");
		btnRetirar.setForeground(Color.BLACK);
		btnRetirar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRetirar.setBackground(Color.LIGHT_GRAY);
		btnRetirar.setBounds(146, 399, 214, 23);
		panel_Inicio.add(btnRetirar);
		
		JButton btnNewButton_7 = new JButton("Consultar saldo");
		btnNewButton_7.setForeground(Color.BLACK);
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_7.setBackground(Color.LIGHT_GRAY);
		btnNewButton_7.setBounds(146, 439, 214, 23);
		panel_Inicio.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Cargar datos");
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_8.setBackground(Color.LIGHT_GRAY);
		btnNewButton_8.setBounds(146, 479, 214, 23);
		panel_Inicio.add(btnNewButton_8);
		btnNewButton_8.setForeground(Color.BLACK);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/img/infra.png")));
		lblNewLabel.setBounds(177, 22, 150, 40);
		panel_Inicio.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/img/maquina-de-casino.png")));
		lblNewLabel_1.setBounds(433, 435, 80, 80);
		panel_Inicio.add(lblNewLabel_1);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/transferencia-de-datos.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/sencillo-formato-de-datos.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				try {

					String nombreArchivo = (String) JOptionPane.showInputDialog(null,"Ingrese el nombre del archivo que desea enviarr: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon,null, null);


					if (nombreArchivo != null) {
						if (!nombreArchivo.isEmpty()) {
							init();
							String fromUser2 = "CARGA" + "," + nombreArchivo;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer = EchoTCPClientProtocol.fromNetwork.readLine();

							init();

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
								JOptionPane.showMessageDialog(null, "Transaccion: " + cadena + fromServer2,"Carga de datos", JOptionPane.PLAIN_MESSAGE, icon2);

								cerrar();

								if (i < lista.length - 2) {

									init();
								}
							}
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
						}
					}

				} catch (Exception x) {

				}
			}
		});
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/numCuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));	
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");


				try {

					String numCuenta = (String) JOptionPane.showInputDialog(null,"Ingrese el numero de la cuenta a cancelar: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon2,null, null);

					if (numCuenta != null && !numCuenta.isEmpty()) {
						init();
						String fromUser2 = "CONSULTAR_SALDO" + "," + numCuenta;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						System.out.println();

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						
						if(fromServer2.contains("El saldo de la cuenta es de")) {
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
						}else {
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon5);
						}


						cerrar();
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/numCuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon4 = new ImageIcon(VistaPrincipal.class.getResource("/img/retiro-de-dinero.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				try {

					String numCuenta = (String) JOptionPane.showInputDialog(null,"Ingrese el numero de la cuenta a cancelar: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon2,null, null);
					String valor = (String) JOptionPane.showInputDialog(null, "Ingrese el valor a depositar","Valor de deposito",JOptionPane.PLAIN_MESSAGE,icon4,null,null);

					if (numCuenta != null && valor != null && !numCuenta.isEmpty() && !valor.isEmpty()) {
						init();
						int numeroCuenta = Integer.parseInt(numCuenta);
						double valorRetiro = Double.parseDouble(valor);

						String fromUser2 = "RETIRAR" + "," + numeroCuenta + "," + valorRetiro;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						
						if(fromServer2.contains("Retiro Exitoso")) {
							
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
						}else {
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon5);	

						}

						cerrar();
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}

		});
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/numCuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon4 = new ImageIcon(VistaPrincipal.class.getResource("/img/saco-de-dinero.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				try {

					String numCuenta = (String) JOptionPane.showInputDialog(null,"Ingrese el numero de la cuenta a cancelar: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon2,null, null);
					String valorDeposito = (String) JOptionPane.showInputDialog(null, "Ingrese el valor a depositar","Valor de deposito",JOptionPane.PLAIN_MESSAGE,icon4,null,null);

					if (numCuenta != null && valorDeposito != null && !numCuenta.isEmpty() && !valorDeposito.isEmpty()) {

							init();
							int numCuentaCliente = Integer.parseInt(numCuenta);
							double saldo = Double.parseDouble(valorDeposito);

							String fromUser2 = "DEPOSITAR" + "," + numCuentaCliente + "," + saldo;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							
							if(fromServer2.contains("Deposito Exitoso")) {
								
								JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
							}else {
								JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon5);	

							}
						
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
						}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnCancelarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/numCuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon4 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");
				
				try {

					String numCuenta = (String) JOptionPane.showInputDialog(null,"Ingrese el numero de la cuenta a cancelar: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon2,null, null);

					if (numCuenta != null && !numCuenta.isEmpty()) {
						init();
						int numeroCuenta = Integer.parseInt(numCuenta);
						String fromUser2 = "CANCELAR" + "," + numeroCuenta;
						EchoTCPClientProtocol.toNetwork.println(fromUser2);

						String fromServer2;
						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						
						if(fromServer2.contains("Cuenta cancelada Exitosamente")) {
							
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
						}else {
							JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon4);	
						}
						
						cerrar();
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
					}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnSortear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/apuesta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon4 = new ImageIcon(VistaPrincipal.class.getResource("/img/dados.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
			
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");
				
				try {

					String numGanador = (String) JOptionPane.showInputDialog(null, "Ingrese el numero a sortear:","Sorteo",JOptionPane.PLAIN_MESSAGE,icon4,null,null);

					if (numGanador != null && !numGanador.isEmpty()) {
					
							init();
							int numeroGanador = Integer.parseInt(numGanador);

							String fromUser2 = "SORTEO" + "," + numeroGanador;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							
							if(fromServer2.contains("Felicitaciones por ganar")) {
								
								JOptionPane.showMessageDialog(null, fromServer2, "Ganador", JOptionPane.PLAIN_MESSAGE, icon2);	
							}else {
								JOptionPane.showMessageDialog(null, fromServer2, "No encontrado", JOptionPane.PLAIN_MESSAGE, icon5);	

							}

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
						}

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnReporteApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String reporte="";
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
		
			
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");
				try {

					init();
					String fromUser2 = "REPORTAR";
					EchoTCPClientProtocol.toNetwork.println(fromUser2);

					String fromServer2;
					fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
					
					String aux= fromServer2;
					
					String reportes[] = aux.split("-");
					
					for(int i=0;i<reportes.length;i++) {
						
						reporte += reportes[i] +"\n";
					}
					
					JOptionPane.showMessageDialog(null, reporte, "Reporte", JOptionPane.PLAIN_MESSAGE, icon);
					cerrar();

				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/usuario-verificado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				
				String fromServer="";
				String fromUser="";
				String respuestaPregunta="";
				String fromUser2="";
				String fromServer2="";

				try {
					
					init();
					
					fromUser = "CERRAR";
					EchoTCPClientProtocol.toNetwork.println(fromUser);
					
					
					fromServer =EchoTCPClientProtocol.fromNetwork2.readLine();
					
					if(fromServer.contains("¿Está seguro de cerrar las apuestas?")) {
							
						respuestaPregunta = (String) JOptionPane.showInputDialog(null,fromServer+"\n\nIngrese SI o NO: ", "Cuenta", JOptionPane.PLAIN_MESSAGE, icon,null, null);
						
						
						fromUser2 = respuestaPregunta;
						EchoTCPClientProtocol.toNetwork2.println(fromUser2);
						

						fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
						
						if(fromServer2.contains("Apuestas cerradas")) {
							JOptionPane.showMessageDialog(null, fromServer2, "Verificación", JOptionPane.PLAIN_MESSAGE, icon2);			
						}else {
							JOptionPane.showMessageDialog(null, fromServer2, "Verificación", JOptionPane.PLAIN_MESSAGE, icon3);	

						}		
						
						cerrar();
					
					}else {
						
						System.out.println("[Client] From server: " + fromServer);
						cerrar();
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/numCuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon4 = new ImageIcon(VistaPrincipal.class.getResource("/img/dados.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/tipo.png"));
				Icon icon6 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));
				
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				try {

					String numCuenta = (String) JOptionPane.showInputDialog(null, "Ingrese el numero de la cuenta:","Cuenta",JOptionPane.PLAIN_MESSAGE,icon2,null,null);
					String numeroApuesta = (String) JOptionPane.showInputDialog(null, "Ingrese el numero a apostar:","Apuesta",JOptionPane.PLAIN_MESSAGE,icon4,null,null);
					String tipoApuesta = (String) JOptionPane.showInputDialog(null,"Ingrese el tipo de la apuesta:","Apuesta",JOptionPane.PLAIN_MESSAGE,icon5,null,null);

					if (numCuenta != null && numeroApuesta!= null && tipoApuesta!=null) {
						if (!numCuenta.isEmpty() && !numeroApuesta.isEmpty() && !tipoApuesta.isEmpty()) {

							init();
							int numCuentaCliente = Integer.parseInt(numCuenta);


							String fromUser2 = "APOSTAR" + "," + numCuentaCliente + "," + tipoApuesta+"," + numeroApuesta;
							EchoTCPClientProtocol.toNetwork.println(fromUser2);

							String fromServer2;
							fromServer2 = EchoTCPClientProtocol.fromNetwork.readLine();
							
							if(fromServer2.contains("Apuesta Exitosa")) {
								
								JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
							}else {
								JOptionPane.showMessageDialog(null, fromServer2, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon6);	

							}

							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
						}
					}

				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Icon icon = new ImageIcon(VistaPrincipal.class.getResource("/img/comprobado.png"));
				Icon icon2 = new ImageIcon(VistaPrincipal.class.getResource("/img/cuenta.png"));
				Icon icon3 = new ImageIcon(VistaPrincipal.class.getResource("/img/formulario-de-contacto.png"));
				Icon icon5 = new ImageIcon(VistaPrincipal.class.getResource("/img/boton-x.png"));	
		
				UIManager.put("OptionPane.background", new Color(180, 170, 160));
				UIManager.put("Panel.background",new Color(180, 170, 160));
				UIManager.put("Button.background", Color.white);
				UIManager.put("OptionPane.okButtonText","Aceptar");

				
				try {
					//String nombreCliente = JOptionPane.showInputDialog(null, "<html><p style=\"color:gray \">Ingrese su nombre</p></html>");
					String nombreCliente= (String) JOptionPane.showInputDialog(null,"Ingrese su nombre: ","Crear cuenta",JOptionPane.PLAIN_MESSAGE,icon2,null,null);
			
					if (nombreCliente != null) {
						if (!nombreCliente.isEmpty()) {

							init();
							String fromUser = "CREAR_CUENTA" + "," + nombreCliente;
							EchoTCPClientProtocol.toNetwork.println(fromUser);

							String fromServer;
							fromServer = EchoTCPClientProtocol.fromNetwork.readLine();

							if(fromServer.contains("Transaccion Exitosa")) {
								
								JOptionPane.showMessageDialog(null, fromServer, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon);	
							}else {
								JOptionPane.showMessageDialog(null, fromServer, "Validacion de cuenta", JOptionPane.PLAIN_MESSAGE, icon5);	

							}
				
							cerrar();
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese la informacion","Error",JOptionPane.PLAIN_MESSAGE,icon3);
						}
					}
				} catch (Exception x) {
					x.printStackTrace();
				}

			}
		});

	}

	public void init() throws Exception {
		clientSideSocket = new Socket(SERVER, PORT);
		EchoTCPClientProtocol.createStreams(clientSideSocket);

	}

	public void cerrar() throws Exception {
		clientSideSocket.close();
	}
}
