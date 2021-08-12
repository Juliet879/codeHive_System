package com.example.studentregistration.api

import android.content.Context
import com.example.studentregistration.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
//    fun<T> buildApiClient(apiInterface: Class<T>,) {
       var authToken:String?=null
        val retrofit by lazy {
//            val client =OkHttpClient.Builder()
//                .addInterceptor{chain ->
//                        chain.proceed(chain.request().newBuilder().also {
//                            it.addHeader("Authorization","Bearer ${authToken}")
//                        }.build())
//
//                    }.also { client ->
//                    if (BuildConfig.DEBUG){
//                        val logging = HttpLoggingInterceptor()
//                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//                        client.addInterceptor(logging)
//                    }
//                }.build()


            Retrofit.Builder()
            .baseUrl("http://13.244.243.129")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
        val api : ApiInterface by lazy {
            retrofit.create(ApiInterface::class.java)
        }

    private fun okhttplClient(context: Context):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor(context))
            .build()
    }
}

