package com.isen.mehto.api

class WeatherService {
    val apiKey: String = "TOKEN"

    fun getWeather(lat: String, lon: String) {
        val httpRequests = HttpRequests()
        httpRequests.get("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}") { response, error ->
            if (error != null) {
                println("Erreur lors de la requête GET : ${error.message}")
            } else {
                println("Réponse GET : $response")
            }
        }
    }
}