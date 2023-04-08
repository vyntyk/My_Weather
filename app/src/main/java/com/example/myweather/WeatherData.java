package com.example.myweather;

import java.text.DecimalFormat;

public class WeatherData {
    private double temperature;
    private double windSpeed;
    private double pressure;
    private int humidity;
    private String description;

    public WeatherData(double temperature, double windSpeed, double pressure, int humidity, String description) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.description = description;
    }
    public String getTemperatureString() {
        return "Температура: " + temperature + " °C";
    }
    public String getWindSpeedString() {
        return "Скорость ветра: " + windSpeed + " м/с";
    }
    public String getPressureString() {
        String pressureStr = new DecimalFormat("#0.00").format(pressure / 1.33289474);
        return "Давление: " + pressureStr + " мм.рт.ст";
    }
    public String getHumidityString() {
        return "Влажность: " + humidity + " %";
    }

    public String getDescriptionString() {
        return "Погода сейчас: " + description;
    }
}
