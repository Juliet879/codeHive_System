package com.example.studentregistration.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    lateinit var apiInterface: ApiInterface
    lateinit var retrofit:Retrofit


    fun buildApiClient(context: Context):ApiInterface {
        retrofit = Retrofit.Builder()
            .baseUrl("http://13.244.243.129")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context))
            .build()
        apiInterface = retrofit.create(apiInterface::class.java)

        return apiInterface

    }

    private fun okhttpClient(context: Context): OkHttpClient  {
        return OkHttpClient .Builder()
            .addInterceptor(OAuthInterceptor(context))
            .build()
    }

}