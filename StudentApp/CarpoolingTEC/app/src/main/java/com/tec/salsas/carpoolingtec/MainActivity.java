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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.conectivity.Client;
import com.tec.salsas.carpoolingtec.conectivity.listeners.ResponseListener;
import com.tec.salsas.carpoolingtec.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private EditText username;
    private EditText password;
    private static Student current;

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public EditText getUsername() {
        return username;
    }

    public void setUsername(EditText username) {
        this.username = username;
    }

    public EditText getPassword() {
        return password;
    }

    public void setPassword(EditText password) {
        this.password = password;
    }

    public Student getCurrent() {
        return current;
    }

    public static void setCurrent(Student current) {
        MainActivity.current = current;
    }

    private final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_page);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton)findViewById(R.id.logFB);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton.setReadPermissions(Arrays.asList("email"));
        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        final Student newStudent = new Student();
        final Context c = this;

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        final LinkedList<String> continuar = new LinkedList<>();
                        GraphRequest request = GraphRequest.newMeRequest( loginResult.getAccessToken()
                                ,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        // Insert your code here
                                        try {
                                            final String name = object.get("name").toString();
                                            final String email = object.get("email").toString();
                                            try {
                                                ObjectMapper mapper = new ObjectMapper();
                                                String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/signup/student";

                                                Map<String, String> prueba1 = new HashMap<>();

                                                prueba1.put("mail",email);

                                                RequestQueue requestQueue = Volley.newRequestQueue(c);
                                                JsonObjectRequest objectRequest = new JsonObjectRequest(
                                                        Request.Method.PUT,
                                                        url,
                                                        new JSONObject(mapper.writeValueAsString(prueba1)),
                                                        new Response.Listener<JSONObject>() {

                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                ObjectMapper mapper = new ObjectMapper();
                                                                try {
                                                                    String prueba = mapper.readValue(response.toString(), HashMap.class).get("result").toString();
                                                                    if(prueba.equals("false")){
                                                                        LoginManager.getInstance().logOut();
                                                                        String text = "Por favor inicie sesion con su correo y contrasenna";
                                                                        Context context = getApplicationContext();
                                                                        int duration = Toast.LENGTH_SHORT;
                                                                        Toast toast = Toast.makeText(context, text, duration);
                                                                        toast.show();
                                                                        return;
                                                                    }else{
                                                                        newStudent.setName(name);
                                                                        newStudent.setEmail(email);
                                                                        Intent intent = new Intent(c, signUp.class);
                                                                        mapper = new ObjectMapper();
                                                                        try {
                                                                            intent.putExtra("data",mapper.writeValueAsString(newStudent));
                                                                            startActivity(intent);
                                                                        } catch (IOException e) {
                                                                            e.printStackTrace();
                                                                        }
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


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (JsonGenerationException e) {
                                                e.printStackTrace();
                                            } catch (JsonMappingException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,picture");
                        request.setParameters(parameters);
                        request.executeAsync();


                    }

                    @Override
                    public void onCancel() {
                        // App code
                        System.out.println("nay");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        System.out.println(exception);
                    }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * Abre la pagina de sign up
     * */
    public void openSignUp(View v){

        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);

    }

    /**
     * Abre la aplicacion principal
     * */
    public void login(View v) throws IOException, JSONException, InterruptedException, TimeoutException {
        String email = username.getText().toString();
        final String pass = password.getText().toString();

        ObjectMapper mapper = new ObjectMapper();
        String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/login/student";

        Map<String, String> prueba1 = new HashMap<>();

        prueba1.put("mail",email);
        prueba1.put("pass",pass);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Context c= this;
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                new JSONObject(mapper.writeValueAsString(prueba1)),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                Student LogInResult = mapper.readValue(response.toString(), Student.class);

                                CharSequence text;

                                if(LogInResult.getCarne().equals("none")){
                                    text = "Por favor registrese";
                                    Context context = getApplicationContext();
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }else if(!LogInResult.getPass().equals(pass)){
                                    text = "Contrasenna incorrecta";
                                    Context context = getApplicationContext();
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                                else{
                                    Intent intent = new Intent(c, main.class);

                                    intent.putExtra("user", response.toString());

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
