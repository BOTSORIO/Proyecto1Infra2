package model;

import java.io.Serializable;

public class Apuesta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numeroCuenta;
	private char tipo;
	private int numeroApuesta;
	
	
	/**
	 * 
	 * @param numeroCuenta
	 * @param tipo
	 * @param numeroApuesta
	 */
	public Apuesta(String numeroCuenta, char tipo, int numeroApuesta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.numeroApuesta = numeroApuesta;
	}


	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	/**
	 * @return the tipo
	 */
	public char getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}


	/**
	 * @return the numeroApuesta
	 */
	public int getNumeroApuesta() {
		return numeroApuesta;
	}


	/**
	 * @param numeroApuesta the numeroApuesta to set
	 */
	public void setNumeroApuesta(int numeroApuesta) {
		this.numeroApuesta = numeroApuesta;
	}

}
