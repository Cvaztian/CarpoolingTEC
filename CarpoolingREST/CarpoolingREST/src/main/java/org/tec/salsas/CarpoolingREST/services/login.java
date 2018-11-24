package org.tec.salsas.CarpoolingREST.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import database.Database;

import org.tec.salsas.CarpoolingREST.model.Student;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase encargada de la conexion del servidor con el cliente
 * @author Kevin Zeledon
 * */
@Path("/login")
public class login {
	
	
	/**
	 * Ejemplo.
	 * @deprecated
	 * */
	@GET
	@Path("/example")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> example(){
		ArrayList<String> list = new ArrayList<>();
		list.add("kev_sala@outlook.com");
		list.add("asdasd");
		return list;
	}
	
	/**
	 * Chequea que los datos de inicio de sesion esten bien.
	 * */
	@PUT
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student retrieve(String dataList_raw) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String,String> dataList = mapper.readValue(dataList_raw, HashMap.class);
		
		String email = dataList.get("mail");
		String password = dataList.get("pass");
		System.out.println(email);
		System.out.println(password);
		return (Student)Database.checkLogin("student", email, password);
	}
	
	
	
}
