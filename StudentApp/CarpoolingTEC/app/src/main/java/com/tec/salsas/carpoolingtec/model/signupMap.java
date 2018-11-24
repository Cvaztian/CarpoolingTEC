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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class signupMap extends AppCompatActivity {

        Student current;
        ImageView residencia, punto1, punto2, punto3, punto4, punto5, punto6, punto7, punto8, punto9, punto10, punto11, punto12, punto13, punto14, punto15, punto16, punto17, punto18, punto19, punto20, punto21, punto22, punto23, punto24, punto25, punto26, punto27, punto28, punto29, punto30, usuario;
        Boolean clickable;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up_map);
            NavigationView navigationView = findViewById(R.id.nav_view);
            View header = navigationView.getHeaderView(0);
            TextView nameT = header.findViewById(R.id.nameTextView);
            TextView emailT = header.findViewById(R.id.mailTextView);

            nameT.setText(current.getName());
            emailT.setText(current.getEmail());

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

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = findViewById(R.id.drawer_layout);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);

            toggle.syncState();
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        public void seleccion(View v) {
            ImageView a = findViewById(v.getId());
            if (clickable) {
                if (this.residencia == null) {
                    a.setImageResource(R.drawable.punterocasa);
                    this.residencia = a;
                    this.clickable = false;
                }
            }
        }

        public void button2(View v) {
            this.clickable = true;
        }
    }

