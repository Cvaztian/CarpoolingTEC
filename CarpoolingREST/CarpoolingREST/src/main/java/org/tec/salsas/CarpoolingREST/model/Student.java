package org.tec.salsas.CarpoolingREST.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modelo del estudiante, el pasajero, el cliente, etc...
 * @author Kevin Zeledon
 * */
@XmlRootElement
public class Student {
	
	private String carne;
	private String name;
	private String email;
	private String pass;
	private String nodoResidencia;
	
	public Student() {
		
	}
	

	public Student(String carne, String name, String email, String nodoResidencia, String pass) {
		// TODO Auto-generated constructor stub
		this.carne = carne;
		this.name = name;
		this.email = email;
		this.nodoResidencia = nodoResidencia;
		this.pass = pass;
	}

	public String getCarne() {
		return carne;
	}

	public void setCarne(String carne) {
		this.carne = carne;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNodoResidencia() {
		return nodoResidencia;
	}

	public void setNodoResidencia(String nodoResidencia) {
		this.nodoResidencia = nodoResidencia;
	}
	
	
	
}
