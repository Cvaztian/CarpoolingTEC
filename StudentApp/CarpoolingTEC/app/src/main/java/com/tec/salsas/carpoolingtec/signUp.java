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

import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tec.salsas.carpoolingtec.model.signupMap;


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

    public void MapButton(View v){
        Intent intent = new Intent(this, signupMap.class);
        startActivity(intent);
    }
    /**
     * Metodo que se ejecuta al oprimir el boton CameraButton y que inicializa el lector de codigo de barras
     * @param v pantalla donde se mostrara
     */
    public void CameraButton(View v){
            IntentIntegrator intent = new IntentIntegrator(this);
            intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
            intent.setPrompt("Coloque su carnet dentro del rectángulo");
            intent.setCameraId(0);
            intent.setBeepEnabled(true);
            intent.setBarcodeImageEnabled(false);
            intent.initiateScan();
        }

    /**
     * Muestra el resultado de la lectura del codigo de barras
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Cancelaste el escaneo", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
