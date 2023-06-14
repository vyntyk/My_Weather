package com.example.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    TextView resultat;
    TextView resultat2;
    TextView resultat3;
    TextView resultat4;
    TextView resultat5;
    private ConstraintLayout mBackground;
    private EditText user_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultat = findViewById(R.id.resultat);
        resultat2 = findViewById(R.id.resultat2);
        resultat3 = findViewById(R.id.resultat3);
        resultat4 = findViewById(R.id.resultat4);
        resultat5 = findViewById(R.id.resultat5);

        mBackground = findViewById(R.id.background);

        Button main_btn = findViewById(R.id.main_btn);

        user_field = findViewById(R.id.user_field);

        main_btn.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            String city = user_field.getText().toString().trim();
            if (!city.isEmpty()) {
                new WeatherApiService(MainActivity.this).execute(city);
            }else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateWeatherData(WeatherData data) {
        resultat.setText(data.getTemperatureString());
        resultat2.setText(data.getWindSpeedString());
        resultat3.setText(data.getPressureString());
        resultat4.setText(data.getHumidityString());
        resultat5.setText(data.getDescriptionString());
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