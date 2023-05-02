/*
 * Created by Viktor Belous on 02.05.2023, 16:24
 * Last modified 02.05.2023, 16:24
 * Copyright (c) 2023.
 * All rights reserved.
 */

package com.example.myweather

import android.util.Pair
import java.net.URL
import org.json.JSONObject

fun getCoordinates(cityName: String): Pair<Double, Double>? {
    val apiKey = "b5bc683aeb3d41156b638602b31704ed"
    val apiUrl = "http://api.openweathermap.org/geo/1.0/direct?q=$cityName&appid=$apiKey"
    val response = URL(apiUrl).toString()
    val jsonResponse = JSONObject(response)
    return if (jsonResponse.has("coord")) {
        val coordinates = jsonResponse.getJSONObject("coord")
        Pair(coordinates.getDouble("lat"), coordinates.getDouble("lon"))
    } else {
        null
    }
}