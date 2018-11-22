package org.tec.salsas.CarpoolingREST.model;


/**
 * Modelo del estudiante, el pasajero, el cliente, etc...
 * @author Kevin Zeledon
 * */
public class Student {
	
	private int carne;
	private String name;
	private String email;
	private String imageUrl;
	private int nodoResidencia;
	
	public Student() {
		
	}

	public Student(int carne, String name, String email, String imageUrl, int nodoResidencia) {
		this.carne = carne;
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.nodoResidencia = nodoResidencia;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getCarne() {
		return carne;
	}

	public void setCarne(int carne) {
		this.carne = carne;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNodoResidencia() {
		return nodoResidencia;
	}

	public void setNodoResidencia(int nodoResidencia) {
		this.nodoResidencia = nodoResidencia;
	}
	
	
	
}
