package com.example.certificacion_books_dlassallec.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksRetrofitClient {

    companion object{
        private const val BASE_URL= "https://my-json-server.typicode.com/Himuravidal/anchorBooks/"

        fun retrofitInstance(): BooksApi {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(BooksApi::class.java)
        }

    }
}