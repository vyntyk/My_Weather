package com.example.myweather;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiService extends AsyncTask <String, Void, WeatherData> {
    private static final String API_KEY = "b5bc683aeb3d41156b638602b31704ed";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    private MainActivity2 mainActivity;

    public WeatherApiService(MainActivity2 mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected WeatherData doInBackground(String... params) {
        String cityName = params[0];
        String url = String.format(API_URL, cityName, API_KEY);

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) apiUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
            urlConnection.disconnect();

            WeatherDataParser parser = new WeatherDataParser();
            return parser.parse(result.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(WeatherData result) {
        super.onPostExecute(result);

        if (result != null) {
            mainActivity.updateWeatherData(result);
        } else {
            Toast toast = Toast.makeText(mainActivity, R.string.bad_input, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
