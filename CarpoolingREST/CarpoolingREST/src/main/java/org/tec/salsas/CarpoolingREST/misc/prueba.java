package org.tec.salsas.CarpoolingREST.misc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class prueba {
	
	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		URL url = new URL("http://localhost:8080/CarpoolingREST/webapi/login/student");
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type", "application/json");
		//Parametros
		
		ArrayList<String> prueba = new ArrayList<>();
		prueba.add("kev_sala@outlook.com");
		prueba.add("asdasd");
		
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(mapper.writeValueAsString(prueba));
		out.flush();
		out.close();
		
		//Leyendo
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		System.out.println(content.toString());
		in.close();
		con.disconnect();
	}
}