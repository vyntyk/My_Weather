package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {
    private EditText user_field;
    private Button main_btn;
    private TextView resultat;
    private TextView resultat2;
    private TextView resultat3;
    private TextView resultat4;
    private TextView resultat5;
    private RelativeLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBackground=(RelativeLayout) findViewById(R.id.background);

        user_field = findViewById(R.id.user_field);
        main_btn = findViewById(R.id.main_btn);
        resultat = findViewById(R.id.resultat);
        resultat2 = findViewById(R.id.resultat2);
        resultat3 = findViewById(R.id.resultat3);
        resultat4 = findViewById(R.id.resultat4);
        resultat5 = findViewById(R.id.resultat5);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_input, Toast.LENGTH_LONG).show();
                else {
                    String city = user_field.getText().toString();
                    String key = "b5bc683aeb3d41156b638602b31704ed";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="
                            + city + "&appid=" + key + "&units=metric&lang=ru";
                    new GetURLData().execute(url);
                }
            }
        });
    }

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

    private class GetURLData extends AsyncTask < String, String, String > {
        private Object JSONValue;
        private Object[] weather;

        protected void onPreExecute() {
            super.onPreExecute();
            resultat.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject object = new JSONObject(result);

                resultat.setText("Температура: " + object.getJSONObject("main").getDouble("temp") + " °C");
                resultat2.setText("Скорость ветра: " + object.getJSONObject("wind").getDouble("speed") + " м/с");
                resultat3.setText("Давление: " + object.getJSONObject("main").getString("pressure") + " гПа");
                resultat4.setText("Влажность: " + object.getJSONObject("main").getString("humidity") + " %");

                JSONObject weather = object.getJSONArray("weather").getJSONObject(0);
                resultat5.setText("Погода сейчас: " + weather.getString("description"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}