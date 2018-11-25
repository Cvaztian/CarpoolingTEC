package org.tec.salsas.CarpoolingREST.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.tec.salsas.CarpoolingREST.Processing.Mapa;

@Path("/setup")
public class setUp {
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void setup() throws Exception {
		
		Mapa.graph = new SingleGraph(null);
		
		Mapa.generateMap();
		
		System.out.println("Created");
	}
	
}
