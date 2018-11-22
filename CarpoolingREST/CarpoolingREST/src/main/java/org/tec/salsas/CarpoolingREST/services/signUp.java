package org.tec.salsas.CarpoolingREST.services;

import java.io.File;
import java.util.ArrayList;

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
	public boolean isRegistered(ArrayList<String> dataList) throws Exception {
		File prueba = Database.findFile("student", dataList.get(0));
		if(prueba==null) {
			return true; 
		}else {
			return false;
		}
	}
	
	/**
	 * Registra un nuevo usuario en la base de datos
	 * @throws Exception 
	 * */
	@POST
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void register(Student newStudent) throws Exception {
		Database.WriteXML("student", newStudent);
	}
	
}