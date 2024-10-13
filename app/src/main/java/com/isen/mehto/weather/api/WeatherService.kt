package com.isen.mehto.weather.api

data class Coordinates(val latitude: String, val longitude: String)

class WeatherService {
    private val apiKey: String = "TOKEN"
    private val httpRequests = HttpRequests()

    fun getWeather(lat: String, lon: String) {
        httpRequests.get("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}") { response, error ->
            if (error != null) {
                println("Erreur lors de la requête GET : ${error.message}")
            } else {
                println("Réponse GET : $response")
            }
        }
    }
    fun getWeather(timeRange: String, lat: String, lon: String) {
        httpRequests.get("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}") { response, error ->
            if (error != null) {
                println("Erreur lors de la requête GET : ${error.message}")
            } else {
                println("Réponse GET : $response")
            }
        }
    }

    private fun getCoordinatesFromCity(city: String, country: String) {
        httpRequests.get("http://api.openweathermap.org/geo/1.0/direct?q=$city,$country&limit=5&appid=$apiKey") { response, error ->
            if (error != null) {
                println("Request error: ${error.message}")
            } else {
                println("GET response: $response")
            }
        }
    }
}