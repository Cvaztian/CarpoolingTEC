package com.tec.salsas.carpoolingtec;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.model.DriverRun;
import com.tec.salsas.carpoolingtec.model.Student;
import com.tec.salsas.carpoolingtec.model.StudentRun;

import java.io.IOException;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static android.view.View.ROTATION;
import static android.view.View.ROTATION_X;
import static android.view.View.ROTATION_Y;
import static android.view.View.TRANSLATION_X;
import static android.view.View.TRANSLATION_Y;

public class main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Student precurrent;
    final StudentRun current = new StudentRun();
    ImageView residencia, punto1, punto2, punto3, punto4, punto5, punto6, punto7, punto8, punto9, punto10, punto11 , punto12, punto13, punto14, punto15, punto16, punto17, punto18, punto19, punto20, punto21, punto22, punto23, punto24, punto25, punto26, punto27, punto28, punto29, punto30, usuario;

    Map<Integer, ImageView> dictionary;
    AnimatorSet cadena;
    List<Animator> lista = new ArrayList<>();
    Boolean clickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObjectMapper mapper = new ObjectMapper();

        Intent intent = getIntent();
        String message = intent.getStringExtra("user");
        try {
            precurrent = mapper.readValue(message, Student.class);
            current.setStudent(precurrent);
            current.setMyDriver(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView nameT = (TextView) header.findViewById(R.id.nameTextView);
        TextView emailT = (TextView) header.findViewById(R.id.mailTextView);

        nameT.setText(current.getName());
        emailT.setText(current.getEmail());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cadena = new AnimatorSet();
        punto1 = (ImageView)findViewById(R.id.punto1);
        punto2 = (ImageView)findViewById(R.id.punto2);
        punto3 = (ImageView)findViewById(R.id.punto3);
        punto4 = (ImageView)findViewById(R.id.punto4);
        punto5 = (ImageView)findViewById(R.id.punto5);
        punto6 = (ImageView)findViewById(R.id.punto6);
        punto7 = (ImageView)findViewById(R.id.punto7);
        punto8 = (ImageView)findViewById(R.id.punto8);
        punto9 = (ImageView)findViewById(R.id.punto9);
        punto10 = (ImageView)findViewById(R.id.punto10);
        punto11 = (ImageView)findViewById(R.id.punto11);
        punto12 = (ImageView)findViewById(R.id.punto12);
        punto13 = (ImageView)findViewById(R.id.punto13);
        punto14 = (ImageView)findViewById(R.id.punto14);
        punto15 = (ImageView)findViewById(R.id.punto15);
        punto16 = (ImageView)findViewById(R.id.punto16);
        punto17 = (ImageView)findViewById(R.id.punto17);
        punto18 = (ImageView)findViewById(R.id.punto18);
        punto19 = (ImageView)findViewById(R.id.punto19);
        punto20 = (ImageView)findViewById(R.id.punto20);
        punto21 = (ImageView)findViewById(R.id.punto21);
        punto22 = (ImageView)findViewById(R.id.punto22);
        punto23 = (ImageView)findViewById(R.id.punto23);
        punto24 = (ImageView)findViewById(R.id.punto24);
        punto25 = (ImageView)findViewById(R.id.punto25);
        punto26 = (ImageView)findViewById(R.id.punto26);
        punto27 = (ImageView)findViewById(R.id.punto27);
        punto28 = (ImageView)findViewById(R.id.punto28);
        punto29 = (ImageView)findViewById(R.id.punto29);
        punto30 = (ImageView)findViewById(R.id.punto30);
        usuario = (ImageView)findViewById(R.id.usuario);

        dictionary = new HashMap<Integer, ImageView>();
        dictionary.put(1,punto1);
        dictionary.put(2,punto2);
        dictionary.put(3,punto3);
        dictionary.put(4,punto4);
        dictionary.put(5,punto5);
        dictionary.put(6,punto6);
        dictionary.put(7,punto7);
        dictionary.put(8,punto8);
        dictionary.put(9,punto9);
        dictionary.put(10,punto10);
        dictionary.put(11,punto11);
        dictionary.put(12,punto12);
        dictionary.put(13,punto13);
        dictionary.put(14,punto14);
        dictionary.put(15,punto15);
        dictionary.put(16,punto16);
        dictionary.put(17,punto17);
        dictionary.put(18,punto18);
        dictionary.put(19,punto19);
        dictionary.put(20,punto20);
        dictionary.put(21,punto21);
        dictionary.put(22,punto22);
        dictionary.put(23,punto23);
        dictionary.put(24,punto24);
        dictionary.put(25,punto25);
        dictionary.put(26,punto26);
        dictionary.put(27,punto27);
        dictionary.put(28,punto28);
        dictionary.put(29,punto29);
        dictionary.put(30,punto30);
        final Context c= this;

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/trip/student";
                RequestQueue requestQueue = Volley.newRequestQueue(c);
                final ObjectMapper mapper = new ObjectMapper();

                JsonObjectRequest objectRequest = null;
                System.out.println(current.getName());
                try {
                    objectRequest = new JsonObjectRequest(
                            Request.Method.PUT,
                            url,
                            new JSONObject(mapper.writeValueAsString(current)),
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    System.out.println(response);
                                    try {
                                        HashMap<String,String> result = mapper.readValue(response.toString(), HashMap.class);
                                        if(result.get("result").toString().equals("encolado")){
                                            fab.setEnabled(false);
                                            Thread thread = new Thread(){
                                                @Override
                                                public void run(){
                                                    final LinkedList<Boolean> repeat = new LinkedList<>();
                                                    repeat.add(true);
                                                    while(repeat.getFirst()) {
                                                        String url1 = "http://192.168.100.76:8080/CarpoolingREST/webapi/trip/student";
                                                        RequestQueue requestQueue = Volley.newRequestQueue(c);
                                                        final ObjectMapper mapper = new ObjectMapper();

                                                        JsonObjectRequest objectRequest = null;
                                                        System.out.println(current.getName());
                                                        try {
                                                            objectRequest = new JsonObjectRequest(
                                                                    Request.Method.POST,
                                                                    url1,
                                                                    new JSONObject(mapper.writeValueAsString(current)),
                                                                    new Response.Listener<JSONObject>() {

                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            try {
                                                                                HashMap<String, String> result = (HashMap<String, String>) mapper.readValue(response.toString(), HashMap.class);
                                                                                System.out.println(result.toString());
                                                                                if(!result.get("carne").equals("none")){
                                                                                    current.setMyDriver(result.get("mail"));
                                                                                    repeat.removeFirst();
                                                                                    repeat.add(false);
                                                                                }
                                                                            } catch (IOException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        }
                                                                    }, new Response.ErrorListener() {
                                                                @Override
                                                                public void onErrorResponse(VolleyError error) {
                                                                    System.out.println(error.toString());
                                                                }
                                                            }
                                                            );
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                        requestQueue.add(objectRequest);
                                                        try {
                                                            Thread.sleep(500);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                            };
                                            thread.start();
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
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                requestQueue.add(objectRequest);

            }
        });

        conversion_nodo_actual(Integer.parseInt(current.getNodoResidencia()));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Ejecuta la animacion de traslacion de un objeto hacia una ubicacion predefinida
     * @param objeto imagen por trasladar
     * @param llegada identificacion de la ubicacion predefinida
     */
    public void navegar(ImageView objeto, int llegada){
        ImageView destino;
        destino = dictionary.get(llegada);
        System.out.println("Este es el punto de partida: "+objeto.toString());
        System.out.println("X: "+objeto.getX()+" Y: "+objeto.getY());
        System.out.println("Este es el punto de llegada: "+destino.toString());
        System.out.println("X: "+destino.getX()+" Y: "+destino.getY());
        PropertyValuesHolder rvhX = PropertyValuesHolder.ofFloat(ROTATION, destino.getX()-objeto.getX());
        PropertyValuesHolder rvhY = PropertyValuesHolder.ofFloat(ROTATION_X, destino.getY()-objeto.getY());
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(TRANSLATION_X, destino.getX()-objeto.getX());
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(TRANSLATION_Y, destino.getY()-objeto.getY());
        ObjectAnimator rotator = ObjectAnimator.ofPropertyValuesHolder(objeto, rvhX, rvhY);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(objeto, pvhX, pvhY);
        animator.setDuration(5000);
        rotator.setDuration(1000);
        lista.add(rotator);
        lista.add(animator);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            System.out.println("Cammera pressed");
            Intent i = new Intent(this, friendPage.class);
            startActivity(i);
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void seleccion(View v){
    }
    public void conversion_nodo_actual(int a){
        dictionary.get(a).setImageResource(R.drawable.punteroinicio);
    }
     public void button2(View v){
        this.clickable= true;
        navegar(usuario,21);
        navegar(usuario,7);
         navegar(usuario,17);
         navegar(usuario,13);
        cadena.playSequentially(lista);
        cadena.start();
     }
}