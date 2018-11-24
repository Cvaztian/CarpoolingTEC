package com.tec.salsas.carpoolingtec.conectivity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.MainActivity;
import com.tec.salsas.carpoolingtec.conectivity.listeners.ResponseListener;
import com.tec.salsas.carpoolingtec.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void loginRequest(String email, String pass, Context context) throws IOException, JSONException, InterruptedException {

        ResponseListener rListener = new ResponseListener();

        Student result;

        ObjectMapper mapper = new ObjectMapper();
        String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/login/student";

        ArrayList<String> prueba = new ArrayList<>();
        prueba.add(email);
        prueba.add(pass);

        Map<String, String> prueba1 = new HashMap<>();

        prueba1.put("mail",email);
        prueba1.put("pass",pass);

        String ayy = mapper.writeValueAsString(prueba1);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url,
            new JSONObject(mapper.writeValueAsString(prueba1)),
                new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    System.out.println(error.toString());
                }
            }
        );
        requestQueue.add(objectRequest);

        //System.out.println(ResponseListener.getLogInResult().getEmail());

    }

}
