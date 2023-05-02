package com.example.myweather;
/*
 * Created by Viktor Belous on 21.04.2023, 15:08
 * Last modified 21.04.2023, 15:08
 * Copyright (c) 2023.
 * All rights reserved.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeatherDataTest {

    @Test
    public void testGetTemperatureString() {
        WeatherData wd = new WeatherData(26.5, 2.8, 1013.25, 60, "Sunny");
        String expected = "Температура: 26.5 °C";
        assertEquals(expected, wd.getTemperatureString());
    }

    @Test
    public void testGetWindSpeedString() {
        WeatherData wd = new WeatherData(26.5, 2.8, 1013.25, 60, "Sunny");
        String expected = "Скорость ветра: 2.8 м/с";
        assertEquals(expected, wd.getWindSpeedString());
    }

    @Test
    public void testGetPressureString() {
        WeatherData wd = new WeatherData(26.5, 2.8, 1013.25, 60, "Sunny");
        String expected = "Pressure: 760.00 мм.рт.ст";
        assertEquals(expected, wd.getPressureString());
    }

    @Test
    public void testGetHumidityString() {
        WeatherData wd = new WeatherData(26.5, 2.8, 1013.25, 60, "Sunny");
        String expected = "Влажность: 60 %";
        assertEquals(expected, wd.getHumidityString());
    }

    @Test
    public void testGetDescriptionString() {
        WeatherData wd = new WeatherData(26.5, 2.8, 1013.25, 60, "Sunny");
        String expected = "Погода сейчас: Sunny";
        assertEquals(expected, wd.getDescriptionString());
    }
}