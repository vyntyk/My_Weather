package com.example.myweather;

import static android.view.Gravity.CENTER;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

class GetURLData extends AsyncTask < String, String, String > {
    private final MainActivity mainActivity;

    public GetURLData(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.resultat.setText("Ожидайте...");
        mainActivity.resultat2.setText("Ожидайте...");
        mainActivity.resultat3.setText("Ожидайте...");
        mainActivity.resultat4.setText("Ожидайте...");
        mainActivity.resultat5.setText("Ожидайте...");
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

            //получение температуры в Цельсиях
            mainActivity.resultat.setText("Температура: " + object.getJSONObject("main").getDouble("temp") + " °C");

            mainActivity.resultat2.setText("Скорость ветра: " + object.getJSONObject("wind").getDouble("speed") + " м/с");

            //давление в мм.рт.ст с точностью до двух знаков
            String pressure = new DecimalFormat("#0.00").format(object.getJSONObject("main")
                    .getDouble("pressure") / 1.33289474);
            mainActivity.resultat3.setText("Давление: " + pressure + " мм.рт.ст");

            mainActivity.resultat4.setText("Влажность: " + object.getJSONObject("main").getString("humidity") + " %");

            JSONObject weather = object.getJSONArray("weather").getJSONObject(0);
            mainActivity.resultat5.setText("Погода сейчас: " + weather.getString("description"));

        } catch (Exception e) {
            //Обработка неправильно введеного названия города
            Toast toast = Toast.makeText(this.mainActivity, R.string.bad_input, Toast.LENGTH_LONG);
            toast.setGravity(CENTER, 0, 0);
            toast.show();
        }
    }
}
