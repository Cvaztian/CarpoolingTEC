package org.tec.salsas.CarpoolingREST.services;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import database.Database;

import org.tec.salsas.CarpoolingREST.model.Student;
import org.jdom2.Content;

/**
 * Clase encargada de la conexion del servidor con el cliente
 * @author Kevin Zeledon
 * */
@Path("/login")
public class login {
	
	@GET
	@Path("/example")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> example(){
		ArrayList<String> list = new ArrayList<>();
		list.add("kev_sala@outlook.com");
		list.add("asdasd");
		return list;
	}
	
	@PUT
	@Path("/student")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student retrieve(ArrayList<String> dataList) throws Exception {
		System.out.println(dataList.get(0));
		return (Student)Database.checkLogin("student", dataList.get(0), dataList.get(1));
	}
	
}
