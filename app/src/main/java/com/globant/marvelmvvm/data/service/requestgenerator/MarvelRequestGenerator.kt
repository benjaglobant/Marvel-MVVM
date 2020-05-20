package com.globant.marvelmvvm.data.service.requestgenerator

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRequestGenerator {

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val defaultRequest = chain.request()

        val defaultHttpUrl = defaultRequest.url()
        val httpUrl = defaultHttpUrl.newBuilder()
            .addQueryParameter(HASH, HASH_VALUE)
            .addQueryParameter(API_KEY, API_KEY_VALUE)
            .addQueryParameter(TIMESTAMP, TIMESTAMP_VALUE)
            .build()

        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

        chain.proceed(requestBuilder.build())
    }

    private val builder = Retrofit.Builder()
        .baseUrl(MARVEL_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        private const val MARVEL_BASE_URL = "http://gateway.marvel.com/public/"
        private const val HASH = "hash"
        private const val API_KEY = "apikey"
        private const val TIMESTAMP = "ts"
        private const val HASH_VALUE = "e5da5e79fc30ff8995330702183cc5b2"
        private const val API_KEY_VALUE = "d743b40fdc49cc7b534e8e68e43156c7"
        private const val TIMESTAMP_VALUE = "1"
    }
}