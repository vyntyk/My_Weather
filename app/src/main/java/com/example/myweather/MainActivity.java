package com.example.myweather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        user_field = findViewById(R.id.user_field);
        Button main_btn = findViewById(R.id.main_btn);
        main_btn.setOnClickListener(view -> {
            String city = user_field.getText().toString();
            new WeatherApiService(MainActivity.this).execute(city);
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