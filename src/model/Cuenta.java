package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int numeroCuenta;
	private String usuario;
	private double saldo;
	private ArrayList<Apuesta> apuestas;
	
	/**
	 * @param numeroCuenta
	 * @param usuario
	 */
	public Cuenta(int numeroCuenta, String usuario) {
		this.numeroCuenta = numeroCuenta;
		this.usuario = usuario;
		this.saldo=0;
		this.apuestas = new ArrayList<>();
	
	}
	
	public Cuenta(String usuario,double saldo) {
		this.usuario = usuario;
		this.saldo=0;
		this.apuestas = new ArrayList<>();
	}
	
	public Cuenta() {
		
		this.saldo=0;
		this.apuestas = new ArrayList<>();
	}


	/**
	 * @return the numeroCuenta
	 */
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}
	
	

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	


	/**
	 * @return the apuestas
	 */
	public ArrayList<Apuesta> getApuestas() {
		return apuestas;
	}

	/**
	 * @param apuestas the apuestas to set
	 */
	public void setApuestas(ArrayList<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", usuario=" + usuario + ", saldo=" + saldo + "]";
	}
	
	
}
