package com.example.myweather;

import java.text.DecimalFormat;

public class WeatherData {
    private static final String LOADING_TEXT = "Ожидайте...";
    private static final String TEMPERATURE_LABEL = "Температура: ";
    private static final String WIND_SPEED_LABEL = "Скорость ветра: ";
    private static final String PRESSURE_LABEL = "Давление: ";
    private static final String HUMIDITY_LABEL = "Влажность: ";
    private static final String DESCRIPTION_LABEL = "Погода сейчас: ";
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
        return TEMPERATURE_LABEL + temperature + " °C";
    }
    public String getWindSpeedString() {
        return WIND_SPEED_LABEL + windSpeed + " м/с";
    }
    public String getPressureString() {
        String pressureStr = new DecimalFormat("#0.00").format(pressure / 1.33289474);
        return PRESSURE_LABEL + pressureStr + " мм.рт.ст";
    }
    public String getHumidityString() {
        return HUMIDITY_LABEL + humidity + " %";
    }

    public String getDescriptionString() {
        return DESCRIPTION_LABEL + description;
    }
}
