package org.tec.salsas.CarpoolingREST.model;

import java.util.LinkedList;

import org.tec.salsas.CarpoolingREST.Processing.NodoMapa;

/**
 * Esta clase posee atributos especiales para una corrida de la app que no deben ser guardados en la base de datos
 * @author Kevin Zeledon
 * */
public class DriverRun extends Driver {
    
    private LinkedList<Student> pasajeros;
    private LinkedList<NodoMapa> ruta;

    public DriverRun(String carne, String name, String email, String pass, String nodoResidencia, LinkedList<Student> pasajeros, LinkedList<NodoMapa> ruta, Double rate) {
        super(carne, name, email, pass, nodoResidencia, rate);
        this.pasajeros = pasajeros;
        this.ruta = ruta;
    }

    public LinkedList<Student> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(LinkedList<Student> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public LinkedList<NodoMapa> getRuta() {
        return ruta;
    }

    public void setRuta(LinkedList<NodoMapa> ruta) {
        this.ruta = ruta;
    }
}