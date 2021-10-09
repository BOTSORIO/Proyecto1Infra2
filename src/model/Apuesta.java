package model;

import java.io.Serializable;

public class Apuesta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int numeroCuenta;
	private String tipo;
	private int numeroApuesta;
	
	
	/**
	 * 
	 * @param numeroCuenta
	 * @param tipo
	 * @param numeroApuesta
	 */
	public Apuesta(int numeroCuenta, String tipo, int numeroApuesta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.numeroApuesta = numeroApuesta;
	}

	public Apuesta() {

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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
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

	@Override
	public String toString() {
		return "Apuesta [numeroCuenta=" + numeroCuenta + ", tipo=" + tipo + ", numeroApuesta=" + numeroApuesta + "]";
	}
	
	

}
