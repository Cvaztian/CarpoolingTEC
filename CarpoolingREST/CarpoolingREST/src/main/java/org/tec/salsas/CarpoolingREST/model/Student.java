package org.tec.salsas.CarpoolingREST.model;

public class Student {
	
	private int carne;
	private String name;
	private String email;
	private long id;
	
	public Student() {
	}

	public Student(int carne, String name, String email, long id) {
		this.carne = carne;
		this.name = name;
		this.email = email;
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
