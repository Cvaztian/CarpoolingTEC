package com.tec.salsas.carpoolingtec;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.model.DrawView;
import com.tec.salsas.carpoolingtec.model.DriverRun;
import com.tec.salsas.carpoolingtec.model.NodoMapa;
import com.tec.salsas.carpoolingtec.model.Rating;
import com.tec.salsas.carpoolingtec.model.Student;
import com.tec.salsas.carpoolingtec.model.StudentRun;

import java.io.IOException;

import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
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
    ImageView residencia, punto1, punto2, punto3, punto4, punto5, punto6, punto7, punto8, punto9, punto10, punto11, punto12, punto13, punto14, punto15, punto16, punto17, punto18, punto19, punto20, punto21, punto22, punto23, punto24, punto25, punto26, punto27, punto28, punto29, punto0, usuario;
    TextView displayer;
    Map<Integer, ImageView> dictionary;
    AnimatorSet cadena;
    List<Animator> lista = new ArrayList<>();
    Boolean clickable;
    int ETA;
    RelativeLayout marco;
    ArrayList<ImageView> orden;
    final Context c = this;

    private final LinkedList<Boolean> result = new LinkedList<>();

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
        marco = findViewById(R.id.marco);

        nameT.setText(current.getName());
        emailT.setText(current.getEmail());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayer = (TextView) findViewById(R.id.ETA_displayer);
        cadena = new AnimatorSet();
        punto1 = (ImageView) findViewById(R.id.punto1);
        punto2 = (ImageView) findViewById(R.id.punto2);
        punto3 = (ImageView) findViewById(R.id.punto3);
        punto4 = (ImageView) findViewById(R.id.punto4);
        punto5 = (ImageView) findViewById(R.id.punto5);
        punto6 = (ImageView) findViewById(R.id.punto6);
        punto7 = (ImageView) findViewById(R.id.punto7);
        punto8 = (ImageView) findViewById(R.id.punto8);
        punto9 = (ImageView) findViewById(R.id.punto9);
        punto10 = (ImageView) findViewById(R.id.punto10);
        punto11 = (ImageView) findViewById(R.id.punto11);
        punto12 = (ImageView) findViewById(R.id.punto12);
        punto13 = (ImageView) findViewById(R.id.punto13);
        punto14 = (ImageView) findViewById(R.id.punto14);
        punto15 = (ImageView) findViewById(R.id.punto15);
        punto16 = (ImageView) findViewById(R.id.punto16);
        punto17 = (ImageView) findViewById(R.id.punto17);
        punto18 = (ImageView) findViewById(R.id.punto18);
        punto19 = (ImageView) findViewById(R.id.punto19);
        punto20 = (ImageView) findViewById(R.id.punto20);
        punto21 = (ImageView) findViewById(R.id.punto21);
        punto22 = (ImageView) findViewById(R.id.punto22);
        punto23 = (ImageView) findViewById(R.id.punto23);
        punto24 = (ImageView) findViewById(R.id.punto24);
        punto25 = (ImageView) findViewById(R.id.punto25);
        punto26 = (ImageView) findViewById(R.id.punto26);
        punto27 = (ImageView) findViewById(R.id.punto27);
        punto28 = (ImageView) findViewById(R.id.punto28);
        punto29 = (ImageView) findViewById(R.id.punto29);
        punto0 = (ImageView) findViewById(R.id.punto0);
        usuario = (ImageView) findViewById(R.id.usuario);
        orden = new ArrayList<>(Arrays.asList(punto0,punto1,punto2,punto3,punto4,punto5,punto6,punto7,punto8,punto9,punto10,punto11,punto12,punto13,punto14,punto15,punto16,punto17,punto18,punto19,punto20,punto21,punto21,punto23,punto24,punto25,punto26,punto27,punto28,punto29));

        dictionary = new HashMap<Integer, ImageView>();
        dictionary.put(1, punto1);
        dictionary.put(2, punto2);
        dictionary.put(3, punto3);
        dictionary.put(4, punto4);
        dictionary.put(5, punto5);
        dictionary.put(6, punto6);
        dictionary.put(7, punto7);
        dictionary.put(8, punto8);
        dictionary.put(9, punto9);
        dictionary.put(10, punto10);
        dictionary.put(11, punto11);
        dictionary.put(12, punto12);
        dictionary.put(13, punto13);
        dictionary.put(14, punto14);
        dictionary.put(15, punto15);
        dictionary.put(16, punto16);
        dictionary.put(17, punto17);
        dictionary.put(18, punto18);
        dictionary.put(19, punto19);
        dictionary.put(20, punto20);
        dictionary.put(21, punto21);
        dictionary.put(22, punto22);
        dictionary.put(23, punto23);
        dictionary.put(24, punto24);
        dictionary.put(25, punto25);
        dictionary.put(26, punto26);
        dictionary.put(27, punto27);
        dictionary.put(28, punto28);
        dictionary.put(29, punto29);
        dictionary.put(0, punto0);
        final Context c = this;

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
                                        HashMap<String, String> result = mapper.readValue(response.toString(), HashMap.class);
                                        if (result.get("result").toString().equals("encolado")) {
                                            fab.setEnabled(false);
                                            Thread thread = new Thread() {
                                                @Override
                                                public void run() {
                                                    final LinkedList<Boolean> repeat = new LinkedList<>();
                                                    repeat.add(true);
                                                    while (repeat.getFirst()) {
                                                        String url1 = "http://192.168.100.76:8080/CarpoolingREST/webapi/trip/student";
                                                        RequestQueue requestQueue = Volley.newRequestQueue(c);
                                                        final ObjectMapper mapper = new ObjectMapper();

                                                        JsonObjectRequest objectRequest = null;
                                                        System.out.println(current.getName());
                                                        try {
                                                            objectRequest = new JsonObjectRequest(
                                                                    Request.Method.POST,
                                                                    url1,
                                                                    new JSONObject(mapper.writeValueAsString(precurrent)),
                                                                    new Response.Listener<JSONObject>() {

                                                                        @Override
                                                                        public void onResponse(JSONObject response) {
                                                                            try {
                                                                                HashMap<String, String> result = (HashMap<String, String>) mapper.readValue(response.toString(), HashMap.class);
                                                                                System.out.println(result.toString());
                                                                                if (!result.get("carne").equals("none")) {
                                                                                    repeat.removeFirst();
                                                                                    repeat.add(false);
                                                                                    current.setMyDriver(result.get("mail"));
                                                                                    System.out.println(result.get("nodoResidencia"));
                                                                                    generar_carro(Integer.parseInt(result.get("nodoResidencia")));
                                                                                    getRuta();
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
    public void frente(ImageView a){
        a.bringToFront();
    }
    /**
     * Ejecuta la animacion de traslacion de un objeto hacia una ubicacion predefinida
     *
     * @param objeto  Objeto a mover
     * @param llegada identificacion de la ubicacion predefinida
     */
    public void navegar(ImageView objeto, int llegada, int peso) {
        ImageView destino;
        destino = dictionary.get(llegada);
        System.out.println("Este es el punto de partida: " + objeto.toString());
        System.out.println("X: " + objeto.getX() + " Y: " + objeto.getY());
        System.out.println("Este es el punto de llegada: " + destino.toString());
        System.out.println("X: " + destino.getX() + " Y: " + destino.getY());
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(TRANSLATION_X, destino.getX()-20);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(TRANSLATION_Y, destino.getY()-30);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(objeto, pvhX, pvhY);
        animator.setDuration(1500 * peso);
        lista.add(animator);
    }

    ImageView carro;

    public void generar_carro(int a) {
        ImageView b = dictionary.get(a);
        carro = new ImageView(c);
        carro.setX(b.getX()-20);
        carro.setY(b.getY()-30);
        System.out.println("Generado en: " + a);
        carro.setImageResource(R.drawable.carro);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(125, 125);
        carro.setLayoutParams(params);
        marco.addView(carro);
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

    public void seleccion(View v) {
        System.out.println("Id:" + v.getId() + "X: " + v.getX() + " Y: " + v.getY());
    }

    public void conversion_nodo_actual(int a) {
        dictionary.get(a).setImageResource(R.drawable.punteroinicio);
    }

    public void button2(View v) {
        this.clickable = true;
        /*
        navegar(usuario,21);
        navegar(usuario,7);
         navegar(usuario,17);
         navegar(usuario,13);*/
        cadena.playSequentially(lista);
        cadena.start();
    }

    private final LinkedList<NodoMapa> ruta = new LinkedList<>();
    private final LinkedList<Boolean> continuar = new LinkedList<>();

    public void getRuta() throws IOException {
        try {
            ruta.remove();
        } catch (Exception e) {
            System.out.println(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/trip/student/ruta";

        RequestQueue requestQueue = Volley.newRequestQueue(c);
        JsonObjectRequest objectRequest = null;
        try {
            objectRequest = new JsonObjectRequest(
                    Request.Method.PUT,
                    url,
                    new JSONObject(mapper.writeValueAsString(precurrent)),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                JSONArray temp = (JSONArray) response.get("result");
                                for (int i = 0; i < temp.length(); i++) {
                                    ruta.add(mapper.readValue(temp.get(i).toString(), NodoMapa.class));
                                }
                                System.out.println(ruta);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                viaje();
                            } catch (InterruptedException e) {
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
        }
        requestQueue.add(objectRequest);
    }


    public void verify() {
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://192.168.100.76:8080/CarpoolingREST/webapi/trip/student/verify";

        RequestQueue requestQueue = Volley.newRequestQueue(c);
        JsonObjectRequest objectRequest = null;
        try {
            objectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    new JSONObject(mapper.writeValueAsString(current)),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ObjectMapper mapper = new ObjectMapper();
                            try {
                                result.add(mapper.readValue(response.get("result").toString(), Boolean.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
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
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestQueue.add(objectRequest);
    }


    @SuppressLint("WrongCall")
    public void viaje() throws InterruptedException {
        for (NodoMapa nodo : ruta) {
            this.ETA += nodo.getTiempo();
        }



        new CountDownTimer(this.ETA * 1500 +2000, 1000) {

            public void onTick(long millisUntilFinished) {
                if((millisUntilFinished / 1000)-2>=0) {
                    displayer.setText("ETA: " + ((millisUntilFinished / 1000) - 2) + " segundos");
                }
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                displayer.setText("Viaje finalizado");
                Intent intent = new Intent(c, Rating.class);
                startActivity(intent);
                main.super.finish();
            }

        }.start();
        for (int i = 0; i < ruta.size(); i++) {
            if (i+1 != ruta.size()) {
                DrawView drawing = new DrawView(this);
                drawing.setX1((int) dictionary.get(ruta.get(i).getiD()).getX()+42);
                drawing.setY1((int) dictionary.get(ruta.get(i).getiD()).getY()+80);
                drawing.setX2((int) dictionary.get(ruta.get(i + 1).getiD()).getX()+42);
                drawing.setY2((int) dictionary.get(ruta.get(i + 1).getiD()).getY()+85);
                marco.addView(drawing);
            }
            for(ImageView n:orden){
                frente(n);
            }
            frente(carro);
            if(i!=0) {
                navegar(carro, ruta.get(i).getiD(), ruta.get(i).getTiempo());
                cadena.playSequentially(lista);
                cadena.start();
            }
            this.ETA -= ruta.get(i).getTiempo();
            result.add(true);
            verify();
                 /*
                 while(result.getFirst()){
                     Thread.sleep(100);
                 }*/
        }
    }

     /*
     Thread viaje = new Thread(){

         @Override
         public void run(){
             try {
                 getRuta();
                 for(NodoMapa nodo:ruta){
                     navegar(nodo.getiD(), nodo.getTiempo());
                 }
                 cadena.playSequentially(lista);
                 cadena.start();

             } catch (IOException e) {
                 e.printStackTrace();
             }


         }
     };*/
}