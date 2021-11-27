package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText user_field;
    private Button main_btn;
    private TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_btn);
        resultat = findViewById(R.id.resultat);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this,R.string.no_input, Toast.LENGTH_LONG).show();
                else{
                    String city = user_field.getText().toString();
                    String key = "b5bc683aeb3d41156b638602b31704ed";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&appid=" +key+ "&units=metric&lang=ru";


                }

            }
        });

    }
}