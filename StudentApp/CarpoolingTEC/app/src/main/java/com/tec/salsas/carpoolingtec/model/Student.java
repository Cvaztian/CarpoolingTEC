package com.tec.salsas.carpoolingtec.model;

import java.util.LinkedList;

/**
 * Modelo del estudiante, el pasajero, el cliente, etc...
 * @author Kevin Zeledon
 * */

public class Student {

    private String carne;
    private String name;
    private String email;
    private String pass;
    private String nodoResidencia;
    private LinkedList<Driver> amigos;
    private Double rate;


    public Student() {

    }


    public Student(String carne, String name, String email, String nodoResidencia, String pass, LinkedList<Driver>amigos, Double rate) {

        this.carne = carne;
        this.name = name;
        this.email = email;
        this.nodoResidencia = nodoResidencia;
        this.pass = pass;
        this.rate = rate;
        this.amigos = amigos;
    }

    public LinkedList<Driver> getAmigos() {
        return amigos;
    }

    public void setAmigos(LinkedList<Driver> amigos) {
        this.amigos = amigos;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
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