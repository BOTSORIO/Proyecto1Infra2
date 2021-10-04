package model;

import java.io.Serializable;

public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int numeroCuenta;
	private String usuario;
	private double saldo;
	private Apuesta apuesta;
	
	/**
	 * @param numeroCuenta
	 * @param usuario
	 */
	public Cuenta(int numeroCuenta, String usuario) {
		this.numeroCuenta = numeroCuenta;
		this.usuario = usuario;
		this.saldo=0;
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
	 * @return the apuesta
	 */
	public Apuesta getApuesta() {
		return apuesta;
	}

	/**
	 * @param apuesta the apuesta to set
	 */
	public void setApuesta(Apuesta apuesta) {
		this.apuesta = apuesta;
	}
	
	
	
	
	
}
