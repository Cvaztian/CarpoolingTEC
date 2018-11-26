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


/**
 * Clase encargada de la solicitud de los viajes
 * @author Kevin Zeledon
 * */
@Path("/trip")
public class Viaje {
	
	private static LinkedList<Student> colaStudentViajes = new LinkedList<>();
	private static LinkedList<DriverRun> colaDriverViajes = new LinkedList<>();
	private static HashMap<String, DriverRun> viajesAsignados = new HashMap<>();
	
	/**
	 * Encola a un estudiante
	 * @param student Estudiante a encolar
	 * @return Hasmap con una variable true para que el cliente reciba un success
	 * */
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
	public static DriverRun mockup1 = new DriverRun("2018076244","Kevin1","kevin@zeledon.com","1234","10",null,null,5.0D);;
	public static DriverRun mockup2 = new DriverRun("2018076244","Kevin1","kevin@zeledon.com","1234","10",null,null,5.0D);;
	
	/**
	 * Funcion encargada del emparejamiento driver-student
	 * @param student Estudiante a emparejar
	 * @return 
	 * */
	@POST
	@Path("/student")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DriverRun empairing(Student student) {
		// Driver mockup
		LinkedList<Student> mockPasajeros = new LinkedList<>();
		//DriverRun mockup1 = new DriverRun("2018076244","Pablo","pablo@escobar.com","1234","4",mockPasajeros,null,5.0D);
		//mockup2 = new DriverRun("2018076244","Kevin1","kevin@zeledon.com","1234","10",mockPasajeros,null,5.0D);
		
		NodoMapa origen1 = new NodoMapa(Integer.parseInt(mockup1.getNodoResidencia()),false,false,0);
		NodoMapa origen2 = new NodoMapa(Integer.parseInt(mockup2.getNodoResidencia()),false,false,0);
		
		NodoMapa medio1 = new NodoMapa(8, true, false, 1);
		NodoMapa medio2 = new NodoMapa(9, true, false, 2);
		NodoMapa medio3 = new NodoMapa(12, true, false, 3);
		NodoMapa destino = new NodoMapa(1,false,false,4);
		
		LinkedList<NodoMapa> destinos1 = new LinkedList<>();
		destinos1.add(origen1);
		destinos1.add(destino);
		destinos1.add(medio1);
		LinkedList<NodoMapa> destinos2 = new LinkedList<>();
		destinos2.add(origen2);
		destinos2.add(destino);
		destinos2.add(medio1);

		LinkedList<NodoMapa> mockRuta1 = GestorMapa.rutaOptima(Mapa.graph, destinos1);
		LinkedList<NodoMapa> mockRuta2 = GestorMapa.rutaOptima(Mapa.graph, destinos2);

		
		mockup1.setRuta(mockRuta1);
		//colaDriverViajes.add(mockup1);
		mockup2.setRuta(mockRuta2);
		colaDriverViajes.add(mockup2);
		
		DriverRun asignado = searchDriver(student);
		viajesAsignados.put(student.getEmail(), asignado);
		
		return asignado;
	}
	
	/**
	 * Funcion que retorna la ruta al cliente
	 * @param student Estudiante del cual se quiere la ruta
	 * @return Hashmap con la ruta
	 * */
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
	
	
	/**
	 * Funcion para ratear al driver desde studentapp
	 * @param prueba Hashmap con correo y nueva calificacion
	 * @return Hashmap con verificacion
	 * */
	@PUT
	@Path("/rateDriver")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> rateDriver(HashMap<String,String> prueba) {
		System.out.println("Hola");
		String mail = prueba.get("mail");
		Double new_rate = Double.parseDouble(prueba.get("rate"));
		if(mail.toString().equals("\"pablo@escobar.com\"")) {
			Viaje.mockup1.setRate((Viaje.mockup1.getRate()+new_rate)/2);
			System.out.println("La nueva calificacion de "+mockup1.getName()+" es: "+mockup1.getRate());
		}else if(mail.toString().equals("\"kevin@zeledon.com\"")) {
			Viaje.mockup2.setRate((Viaje.mockup2.getRate()+new_rate)/2);
			System.out.println("La nueva calificacion de "+mockup2.getName()+" es: "+mockup2.getRate());
		}else {
			System.out.print(mail);
		}
		HashMap<String, String> result = new HashMap<>();
		result.put("result", "true");
		return result;
	}
	
	
	/**
	 * Funcion que verifica si la ruta del estudiante esta bien
	 * @param student estudiante del cual se necesita averiguar si la ruta esta bien
	 * @return Hashmap verificacion 
	 * */
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
		for(DriverRun driver:colaDriverViajes){
			System.out.println(colaDriverViajes.size());
			for(NodoMapa ubicacion:driver.getRuta()){
				if(Integer.toString(ubicacion.getiD()).equals(student.getNodoResidencia().toString())) {
					
					if(student.getModo().equals("friends")){
						for(Driver amigo:student.getAmigos()) {
							System.out.println(amigo.getName()+" Mio");
							System.out.println(driver.getName()+" El");
							if(amigo.getName().equals(driver.getName())){
								return driver;
							}
						}
						return new DriverRun("none","","","","",new LinkedList<Student>(),new LinkedList<NodoMapa>(),0D);
					}
					
					System.out.println("Returning something");
					return driver;
				}
			}
		}
		return new DriverRun("none","","","","",new LinkedList<Student>(),new LinkedList<NodoMapa>(),0D);
	}
	
}