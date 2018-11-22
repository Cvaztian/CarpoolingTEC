package org.tec.salsas.CarpoolingREST.services;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.tec.salsas.CarpoolingREST.model.Student;

/**
 * Clase encargada de la conexion del servidor con el cliente
 * @author Kevin Zeledon
 * */
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class login {
	
	@PUT
	@Path("/{dataList}")
	public Student retrieve(@PathParam("datalist") ArrayList<Student> dataList) {
		return null;
	}
	
}
