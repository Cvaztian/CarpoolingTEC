package org.tec.salsas.CarpoolingREST.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import database.Database;

import org.tec.salsas.CarpoolingREST.Processing.GestorMapa;
import org.tec.salsas.CarpoolingREST.Processing.Mapa;
import org.tec.salsas.CarpoolingREST.Processing.NodoMapa;
import org.tec.salsas.CarpoolingREST.model.Driver;
import org.tec.salsas.CarpoolingREST.model.DriverRun;
import org.tec.salsas.CarpoolingREST.model.Student;

@Path("/trip")
public class Viaje {
	
	private static LinkedList<Student> colaStudentViajes = new LinkedList<>();
	private static LinkedList<DriverRun> colaDriverViajes = new LinkedList<>();
	private static HashMap<String, DriverRun> viajesAsignados = new HashMap<>();
	
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
		// Driver mockup
		LinkedList<Student> mockPasajeros = new LinkedList<>();
		DriverRun mockup = new DriverRun("2018076244","Pablo","email","1234","4",mockPasajeros,null,5.0D);
		
		NodoMapa origen = new NodoMapa(Integer.parseInt(mockup.getNodoResidencia()),false,false,0);
		NodoMapa medio1 = new NodoMapa(8, true, false, 1);
		NodoMapa medio2 = new NodoMapa(9, true, false, 2);
		NodoMapa medio3 = new NodoMapa(12, true, false, 3);
		NodoMapa destino = new NodoMapa(1,false,false,4);
		
		LinkedList<NodoMapa> destinos = new LinkedList<>();
		destinos.add(origen);
		destinos.add(destino);
		destinos.add(medio1);

		LinkedList<NodoMapa> mockRuta = GestorMapa.rutaOptima(Mapa.graph, destinos);

		//mockRuta.add("8");
		
		mockup.setRuta(mockRuta);
		colaDriverViajes.add(mockup);
		
		DriverRun asignado = searchDriver(student);
		viajesAsignados.put(student.getEmail(), asignado);
		
		return asignado;
	}
	
	@PUT
	@Path("/student/ruta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, LinkedList<NodoMapa>> getRuta(Student student){
		if(viajesAsignados.get(student.getEmail())!=null) {
			System.out.println(viajesAsignados.get(student.getEmail()).getRuta());
			HashMap<String, LinkedList<NodoMapa>> result = new HashMap<>();
			result.put("result", viajesAsignados.get(student.getEmail()).getRuta());
			return result;
		}else {
			return null;
		}
		
	}
	
	@GET
	@Path("/student/verify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Boolean> verifyS(Student student){
		HashMap<String, Boolean> result = new HashMap<>();
		result.put("result", false);
		System.out.println("returning");
		return result;
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
		for(DriverRun driver:colaDriverViajes){
			for(NodoMapa ubicacion:driver.getRuta()){
				System.out.println((ubicacion.getiD()));
				System.out.println(student.getNodoResidencia());
				if(Integer.toString(ubicacion.getiD()).equals(student.getNodoResidencia().toString())) {
					System.out.println("Returning something");
					return driver;
				}
			}
		}
		return new DriverRun("none","","","","",new LinkedList<Student>(),new LinkedList<NodoMapa>(),0D);
	}
	
}