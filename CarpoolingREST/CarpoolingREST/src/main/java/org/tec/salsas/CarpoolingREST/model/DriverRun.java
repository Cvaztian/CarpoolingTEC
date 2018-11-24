package org.tec.salsas.CarpoolingREST.model;

import java.util.LinkedList;

/**
 * Esta clase posee atributos especiales para una corrida de la app que no deben ser guardados en la base de datos
 * @author Kevin Zeledon
 * */
public class DriverRun extends Driver {
    
    private LinkedList<Student> pasajeros;
    private LinkedList<String> ruta;

    public DriverRun(String carne, String name, String email, String pass, String nodoResidencia, LinkedList<Student> pasajeros, LinkedList<String> ruta) {
        super(carne, name, email, pass, nodoResidencia);
        this.pasajeros = pasajeros;
        this.ruta = ruta;
    }

    public LinkedList<Student> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(LinkedList<Student> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public LinkedList<String> getRuta() {
        return ruta;
    }

    public void setRuta(LinkedList<String> ruta) {
        this.ruta = ruta;
    }
}