package com.example.weatherapp.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request= chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "8c91b5560dmsheae043e721e89a0p17120ajsn0ff781417d3f")
            .addHeader("X-RapidAPI-Host", "yahoo-weather5.p.rapidapi.com")
            .build()

        return chain.proceed(request)
    }
}