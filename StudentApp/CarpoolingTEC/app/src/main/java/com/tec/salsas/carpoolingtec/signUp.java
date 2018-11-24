package com.tec.salsas.carpoolingtec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class signUp extends AppCompatActivity {
    private Student newStudent;
    private EditText pass;
    private String nodoResidencia = "1";
    private String carnet = "2018";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectMapper mapper = new ObjectMapper();
        setContentView(R.layout.activity_sign_up);

        pass = (EditText)findViewById(R.id.editText4);

        Intent intent = getIntent();

        try {
            newStudent = mapper.readValue(intent.getStringExtra("data"), Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void done(View v) throws IOException, JSONException {

        final ObjectMapper mapper = new ObjectMapper();
        String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/signup/student";
        newStudent.setCarne(carnet);
        newStudent.setNodoResidencia(nodoResidencia);
        if(pass.getText().toString().isEmpty()){
            //Toast
            String text = "Ingrese una contrasenna";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            // End of toast
            return;
        }
        newStudent.setPass(pass.getText().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Context c= this;
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new JSONObject(mapper.writeValueAsString(newStudent)),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            HashMap<String,String> result = mapper.readValue(response.toString(), HashMap.class);
                            if(result.get("result").equals("true")){
                                Intent intent = new Intent(c, main.class);

                                intent.putExtra("user", newStudent.toString());

                                startActivity(intent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println(error.toString());
            }
        }
        );
        requestQueue.add(objectRequest);
    }
}
