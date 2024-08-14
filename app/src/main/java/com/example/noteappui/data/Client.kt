package com.example.noteappui.data

import android.content.Context
import android.util.Log
import com.example.noteappui.CheckNetConnect
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val checkNetConnect = CheckNetConnect
class TestRetrofit {
    companion object {
        private var instance: Retrofit? = null
        private var context: Context? = null

        fun init(context: Context) {
            this.context = context
        }
        fun getInstance(): Retrofit? {
            if (instance == null && checkNetConnect.isInternetAvailable(context!!)) {
                    val okHttpClient = OkHttpClient.Builder()
                        .connectTimeout(130, TimeUnit.SECONDS)  // Connection timeout
                        .writeTimeout(130, TimeUnit.SECONDS)    // Write timeout
                        .readTimeout(130, TimeUnit.SECONDS)
                        .build()

                    instance = Retrofit.Builder()
                        .baseUrl("http://192.168.1.35:8080/")
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            } else {
                Log.d("TAG", "No internet connection")
            }
            return instance
        }
    }
}