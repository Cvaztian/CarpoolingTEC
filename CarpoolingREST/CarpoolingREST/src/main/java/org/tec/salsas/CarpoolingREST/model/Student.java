package org.tec.salsas.CarpoolingREST.model;

import java.util.LinkedList;

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
	private Double rate;
	private LinkedList<Driver> amigos;
	private String modo;
	
	public Student() {
		
	}
	

	public Student(String carne, String name, String email, String nodoResidencia, String pass, LinkedList<Driver> amigos, Double rate, String modo) {
		// TODO Auto-generated constructor stub
		this.carne = carne;
		this.name = name;
		this.email = email;
		this.nodoResidencia = nodoResidencia;
		this.pass = pass;
		this.amigos = amigos;
		this.rate = rate;
		this.modo = modo;
	}
	

	public Double getRate() {
		return rate;
	}


	public String getModo() {
		return modo;
	}


	public void setModo(String modo) {
		this.modo = modo;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}


	public LinkedList<Driver> getAmigos() {
		return amigos;
	}
	

	public void setAmigos(LinkedList<Driver> amigos) {
		this.amigos = amigos;
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
