package com.tec.salsas.carpoolingtec.model;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
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

    public DriverRun(String carne, String name, String email, String pass, String nodoResidencia, JSONArray pasajeros, JSONArray ruta) throws JSONException, IOException {
        super(carne, name, email, pass, nodoResidencia);

        ObjectMapper mapper = new ObjectMapper();

        if(pasajeros!=null){
            for(int i=0;i<pasajeros.length();i++){
                this.pasajeros.add(mapper.readValue(pasajeros.getString(i), Student.class));
            }
        }else{
            this.pasajeros = null;
        }

        if(ruta !=null){
            for(int i=0;i<ruta.length();i++){
                this.ruta.add(ruta.getString(i));
            }
        }

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

