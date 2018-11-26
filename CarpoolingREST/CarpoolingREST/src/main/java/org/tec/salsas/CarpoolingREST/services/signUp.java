package org.tec.salsas.CarpoolingREST.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import database.Database;

import org.tec.salsas.CarpoolingREST.model.Student;

/**
 * Clase encargada de la conexion del servidor con el cliente
 * @author Kevin Zeledon
 * */
@Path("/signup")
public class signUp {
	
	/**
	 * Verifica si el correo ya esta registrado
	 * */
	@PUT
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String,String> isRegistered(HashMap<String,String> dataList) throws Exception {
		File prueba = Database.findFile("student", dataList.get("mail"));
		HashMap<String,String> result = new HashMap<>();
		if(prueba==null) {
			result.put("result", "true");
		}else {
			result.put("result","false");
		}
		return result;
	}
	
	/**
	 * Registra un nuevo usuario en la base de datos
	 * @throws Exception 
	 * */
	@POST
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String,String> register(Student newStudent) throws Exception {
		System.out.println(newStudent.toString());
		newStudent.setRate(5.0);
		Database.WriteXML("student", newStudent);
		HashMap<String,String> result = new HashMap<>();
		result.put("result","true");
		return result;
	}
	
}