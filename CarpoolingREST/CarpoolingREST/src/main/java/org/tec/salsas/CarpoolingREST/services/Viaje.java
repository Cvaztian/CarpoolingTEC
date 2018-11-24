package org.tec.salsas.CarpoolingREST.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import database.Database;

import org.tec.salsas.CarpoolingREST.model.Driver;
import org.tec.salsas.CarpoolingREST.model.DriverRun;
import org.tec.salsas.CarpoolingREST.model.Student;

@Path("/trip")
public class Viaje {
	
	private static LinkedList<Student> colaStudentViajes = new LinkedList<>();
	private static LinkedList<DriverRun> colaDriverViajes = new LinkedList<>();
	
	@PUT
	@Path("/student")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> enqueue(Student student){
		colaStudentViajes.add(student);
		HashMap<String,String> result = new HashMap<String, String>();
		result.put("result", "encolado");
		return result;
	}
	
	@POST
	@Path("/student")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DriverRun empairing(Student student) {
		return searchDriver(student);
	}
	
	/**
	 * Busca un conductor para un especifico pasajero. El conductor encontrado 
	 * debe tener en sus nodos de ruta el nodo del hogar del pasajero.
	 * */
	private DriverRun searchDriver(Student student) {
		LinkedList<String> pruebaR = new LinkedList<>();
		pruebaR.add("0");
		pruebaR.add("2");
		pruebaR.add("1");
		DriverRun pruebaD = new DriverRun("2018","eu","ala","je","0",new LinkedList<Student>(),pruebaR);
		colaDriverViajes.add(pruebaD);
		for(DriverRun driver:colaDriverViajes){
			for(String ubicacion:driver.getRuta()){
				System.out.println((ubicacion));
				System.out.println(student.getNodoResidencia());
				if(ubicacion.toString().equals(student.getNodoResidencia().toString())) {
					System.out.println("Returning something");
					return driver;
				}
			}
		}
		return new DriverRun("none","","","","",new LinkedList<Student>(),new LinkedList<String>());
	}
	
}