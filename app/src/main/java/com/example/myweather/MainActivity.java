package com.example.myweather;

import static android.view.Gravity.CENTER;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private EditText user_field;
    protected TextView resultat;
    protected TextView resultat2;
    protected TextView resultat3;
    protected TextView resultat4;
    protected TextView resultat5;
    private ConstraintLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBackground = (ConstraintLayout) findViewById(R.id.background);

        user_field = findViewById(R.id.user_field);
        Button main_btn = findViewById(R.id.main_btn);
        resultat = findViewById(R.id.resultat);
        resultat2 = findViewById(R.id.resultat2);
        resultat3 = findViewById(R.id.resultat3);
        resultat4 = findViewById(R.id.resultat4);
        resultat5 = findViewById(R.id.resultat5);

        main_btn.setOnClickListener(view -> {
            if (user_field.getText().toString().trim().equals("")) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.no_input, Toast.LENGTH_LONG);
                toast.setGravity(CENTER, 0, 0);
                toast.show();
            } else {
                String city = user_field.getText().toString();
                String key = "b5bc683aeb3d41156b638602b31704ed";
                String url = "https://api.openweathermap.org/data/2.5/weather?q="
                        + city + "&appid=" + key + "&units=metric&lang=ru";
                new GetURLData(MainActivity.this).execute(url);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void BG(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mBackground.setBackgroundResource(R.drawable.foto3);
                break;
            case R.id.btn2:
                mBackground.setBackgroundResource(R.drawable.foto2);
                break;
            case R.id.btn3:
                mBackground.setBackgroundResource(R.drawable.foto1);
                break;

        }
    }
}