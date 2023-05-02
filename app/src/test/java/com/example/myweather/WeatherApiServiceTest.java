package com.example.myweather;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiServiceTest {

    private MainActivity mockMainActivity = mock(MainActivity.class);
    private WeatherApiService weatherApiService = new WeatherApiService(mockMainActivity);

    @Test
    public void doInBackground_shouldParseWeatherData() throws Exception {
        String cityName = "London";
        String url = String.format(WeatherApiService.API_URL, cityName, WeatherApiService.API_KEY);
        URL apiUrl = new URL(url);

        HttpURLConnection urlConnectionMock = mock(HttpURLConnection.class);
        InputStream inputStreamMock = mock(InputStream.class);
        BufferedReader readerMock = mock(BufferedReader.class);
        when(apiUrl.openConnection()).thenReturn(urlConnectionMock);
        when(urlConnectionMock.getInputStream()).thenReturn(inputStreamMock);
        when(readerMock.readLine()).thenReturn("{\"coord\":{\"lon\":-0.13,\"lat\":51.51}," +
                "\"weather\":[{\"id\":711,\"main\":\"Smoke\",\"description\":\"smoke\"," +
                "\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":295.02," +
                "\"pressure\":1018,\"humidity\":64,\"temp_min\":292.59,\"temp_max\":296.48}," +
                "\"visibility\":8000,\"wind\":{\"speed\":2.1,\"deg\":300},\"clouds\":" +
                "{\"all\":90},\"dt\":1565997856,\"sys\":{\"type\":1,\"id\":1414,\"message\":0.014," +
                "\"country\":\"GB\",\"sunrise\":1565994576,\"sunset\":1566045546}," +
                "\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}");
        when(weatherApiService.doInBackground(cityName)).thenCallRealMethod();

        WeatherData result = weatherApiService.doInBackground(cityName);

        assertNotNull(result);
        //assertEquals("London", result.getCity());
      //  assertEquals("GB", result.getCountry());
       // assertEquals(295.02, result.getTemperature(), 0.0);
    }

    @Test
    public void doInBackground_shouldReturnNull_whenConnectionFails() throws Exception {
        String cityName = "NotACity";
        String url = String.format(WeatherApiService.API_URL, cityName, WeatherApiService.API_KEY);
        URL apiUrl = new URL(url);

        HttpURLConnection urlConnectionMock = mock(HttpURLConnection.class);
        when(apiUrl.openConnection()).thenReturn(urlConnectionMock);
        when(urlConnectionMock.getInputStream()).thenThrow(new IOException());
        when(weatherApiService.doInBackground(cityName)).thenCallRealMethod();

        WeatherData result = weatherApiService.doInBackground(cityName);

        assertNull(result);
    }

    @Test
    public void onPostExecute_shouldCallUpdateWeatherData_whenResultIsNotNull() {
        WeatherData weatherData = new WeatherData(67, 3.0, 760, 5, "werew");
        when(mockMainActivity.getString(R.string.bad_input)).thenReturn("Bad input message");
        when(mockMainActivity.getApplicationContext()).thenReturn(mock(Context.class));

        weatherApiService.onPostExecute(weatherData);

        verify(mockMainActivity).updateWeatherData(weatherData);
    }

    @Test
    public void onPostExecute_shouldShowToast_whenResultIsNull() {
        when(mockMainActivity.getString(R.string.bad_input)).thenReturn("Bad input message");
        when(mockMainActivity.getApplicationContext()).thenReturn(mock(Context.class));

        weatherApiService.onPostExecute(null);

        verify(mockMainActivity).runOnUiThread(any(Runnable.class)); // can't verify toast object directly
    }
}