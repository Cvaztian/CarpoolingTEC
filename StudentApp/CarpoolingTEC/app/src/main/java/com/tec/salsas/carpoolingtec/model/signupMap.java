package com.tec.salsas.carpoolingtec.model;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.R;
import com.tec.salsas.carpoolingtec.friendPage;
import com.tec.salsas.carpoolingtec.signUp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class signupMap extends AppCompatActivity {

        Student current;
        ImageView residencia, punto1, punto2, punto3, punto4, punto5, punto6, punto7, punto8, punto9, punto10, punto11, punto12, punto13, punto14, punto15, punto16, punto17, punto18, punto19, punto20, punto21, punto22, punto23, punto24, punto25, punto26, punto27, punto28, punto29, punto30, usuario;
        Boolean clickable;
        HashMap dictionary;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up_map);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            punto1 = findViewById(R.id.punto1);
            punto2 = findViewById(R.id.punto2);
            punto3 = findViewById(R.id.punto3);
            punto4 = findViewById(R.id.punto4);
            punto5 = findViewById(R.id.punto5);
            punto6 = findViewById(R.id.punto6);
            punto7 = findViewById(R.id.punto7);
            punto8 = findViewById(R.id.punto8);
            punto9 = findViewById(R.id.punto9);
            punto10 = findViewById(R.id.punto10);
            punto11 = findViewById(R.id.punto11);
            punto12 = findViewById(R.id.punto12);
            punto13 = findViewById(R.id.punto13);
            punto14 = findViewById(R.id.punto14);
            punto15 = findViewById(R.id.punto15);
            punto16 = findViewById(R.id.punto16);
            punto17 = findViewById(R.id.punto17);
            punto18 = findViewById(R.id.punto18);
            punto19 = findViewById(R.id.punto19);
            punto20 = findViewById(R.id.punto20);
            punto21 = findViewById(R.id.punto21);
            punto22 = findViewById(R.id.punto22);
            punto23 = findViewById(R.id.punto23);
            punto24 = findViewById(R.id.punto24);
            punto25 = findViewById(R.id.punto25);
            punto26 = findViewById(R.id.punto26);
            punto27 = findViewById(R.id.punto27);
            punto28 = findViewById(R.id.punto28);
            punto29 = findViewById(R.id.punto29);
            punto30 = findViewById(R.id.punto30);
            usuario = findViewById(R.id.usuario);

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

            this.clickable=true;
        }


        public void seleccion(View v) {
            ImageView a = findViewById(v.getId());
            if (clickable) {
                if (this.residencia == null) {
                    a.setImageResource(R.drawable.punterocasa);

                    for(int i=1;i<=30;i++){
                        if(dictionary.get(i) == a){
                            signUp.newStudent.setNodoResidencia(Integer.toString(i));
                        }
                    }

                    this.residencia = a;
                    this.clickable = false;
                }
            }
        }

        public void button2(View v) {
            this.clickable = true;
        }
    }

