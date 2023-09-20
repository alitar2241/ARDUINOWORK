package com.example.localwifihttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.io.BufferedInputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOn = findViewById<Button>(R.id.button)
        val buttonOff = findViewById<Button>(R.id.button2)

        buttonOn.setOnClickListener{
            sendRequest("http://192.168.0.51/LedOn")
            Log.i("CHECK", "ON was checked")
        }

        buttonOff.setOnClickListener{
            sendRequest("http://192.168.0.51/LedOff")
            Log.i("CHECK", "OFF was checked")

        }


    }
}

private fun sendRequest(urlString: String) {
    Thread {
        Log.i("THREAD", "Sending request to: $urlString")

        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection

            connection.requestMethod = "GET"
            connection.connectTimeout = 5000

            val responseCode = connection.responseCode

            Log.i("THREAD", "Response code: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = BufferedInputStream(connection.inputStream)
                // Process the response here
            }

            connection.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("THREAD", "Error: ${e.message}")
        }

    }.start()
}