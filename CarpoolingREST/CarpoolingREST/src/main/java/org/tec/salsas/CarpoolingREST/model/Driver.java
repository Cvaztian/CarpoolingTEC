package org.tec.salsas.CarpoolingREST.model;

/**
 * Modelo del conductor
 * @author Kevin Zeledon
 * */
public class Driver {

    private String carne;
    private String name;
    private String email;
    private String pass;
    private String nodoResidencia;
    private Double rate;

    public Driver(){

    }

    public Driver(String carne, String name, String email, String pass, String nodoResidencia, Double rate) {
        this.carne = carne;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.nodoResidencia = nodoResidencia;
        this.rate = rate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNodoResidencia() {
        return nodoResidencia;
    }

    public void setNodoResidencia(String nodoResidencia) {
        this.nodoResidencia = nodoResidencia;
    }

	public Double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
		System.out.println(this.rate);
	}
	
	
}
