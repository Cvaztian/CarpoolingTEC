package com.tec.salsas.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.salsas.carpoolingtec.model.Driver;
import com.tec.salsas.carpoolingtec.model.Student;

import java.io.IOException;

public class friendPage extends AppCompatActivity {

    LinearLayout mainScroll;
    Student current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ObjectMapper mapper = new ObjectMapper();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);
        mainScroll = (LinearLayout)findViewById(R.id.mainView);
        Intent intent = getIntent();
        String mensaje = intent.getStringExtra("data");
        try {
            current = mapper.readValue(mensaje, Student.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        for(Driver amigo:current.getAmigos()){
            TextView name = new TextView(this);
            //name.setTextColor(c6c9ce);
            name.setTextSize(30);
            name.setText(amigo.getName());
            mainScroll.addView(name, 0);
        }


    }
}
