package com.example.myweather;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataParser {
    public WeatherData parse(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        double temperature = object.getJSONObject("main").getDouble("temp");
        double windSpeed = object.getJSONObject("wind").getDouble("speed");
        double pressure = object.getJSONObject("main").getDouble("pressure");
        int humidity = object.getJSONObject("main").getInt("humidity");
        String description = object.getJSONArray("weather").getJSONObject(0).getString("description");
        return new WeatherData(temperature, windSpeed, pressure, humidity, description);
    }
}
