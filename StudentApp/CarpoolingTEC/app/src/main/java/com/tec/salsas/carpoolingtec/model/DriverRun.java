package com.tec.salsas.carpoolingtec.model;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Esta clase posee atributos especiales para una corrida de la app que no deben ser guardados en la base de datos
 * @author Kevin Zeledon
 * */
public class DriverRun extends Driver {

    private ArrayList<Student> pasajeros;
    private ArrayList<String> ruta;






    public ArrayList<Student> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Student> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public ArrayList<String> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<String> ruta) {
        this.ruta = ruta;
    }
}

