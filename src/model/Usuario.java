package model;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private String nombre;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [cuenta=" + cuenta + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre,other.nombre);
	}
	
	
}
