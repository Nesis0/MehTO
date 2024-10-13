package com.isen.mehto.api

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class HttpRequests {

    private val client = OkHttpClient()

    // Méthode GET
    fun get(url: String, callback: (String?, IOException?) -> Unit) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback(response.body?.string(), null)
            }
        })
    }

    // Méthode POST
    fun post(url: String, json: String, callback: (String?, IOException?) -> Unit) {
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback(response.body?.string(), null)
            }
        })
    }
}
