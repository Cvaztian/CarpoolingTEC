package com.tec.salsas.carpoolingtec.conectivity.listeners;

import com.android.volley.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.model.Student;

import org.json.JSONObject;

import java.io.IOException;

public class ResponseListener implements Response.Listener<JSONObject> {

    private static Student logInResult;

    @Override
    public void onResponse(JSONObject response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            logInResult = mapper.readValue(response.toString(), Student.class);
            System.out.println("Received");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Student getLogInResult() {
        return logInResult;
    }

    public static void setLogInResult(Student logInResult) {
        ResponseListener.logInResult = logInResult;
    }
}
