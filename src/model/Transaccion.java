package model;


public class Transaccion {
	
	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The servicio. */
	private String servicio;
	
	/** The num cuenta. */
	private int numCuenta;
	
	private String tipo;
	
	private int numApuesta;


	public Transaccion(Cuenta cuenta, String servicio, int numCuenta) {
		super();
		this.cuenta = cuenta;
		this.servicio = servicio;
		this.numCuenta = numCuenta;
	}
	
	public Transaccion(Cuenta cuenta, String servicio) {
		super();
		this.cuenta = cuenta;
		this.servicio = servicio;
	}
	
	public Transaccion(String servicio) {
		this.servicio = servicio;
	}
	
	public Transaccion() {
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta 
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the num cuenta.
	 *
	 * @return the num cuenta
	 */
	public int getNumCuenta() {
		return numCuenta;
	}

	/**
	 * Sets the num cuenta.
	 *
	 * @param numCuenta 
	 */
	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	/**
	 * Gets the servicio.
	 *
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * Sets the servicio.
	 *
	 * @param servicio 
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
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
	 * @return the numApuesta
	 */
	public int getNumApuesta() {
		return numApuesta;
	}

	/**
	 * @param numApuesta the numApuesta to set
	 */
	public void setNumApuesta(int numApuesta) {
		this.numApuesta = numApuesta;
	}
	
	


}
