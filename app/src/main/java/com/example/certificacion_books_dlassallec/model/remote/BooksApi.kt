package com.example.certificacion_books_dlassallec.model.remote

import com.example.certificacion_books_dlassallec.model.Books
import com.example.certificacion_books_dlassallec.model.BooksDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {

    @GET("books")
    suspend fun fetchBooksList(): Response<List<Books>>

    @GET("bookDetail/{id}")
    suspend fun fetchBookDetails(@Path("id") id: Int): Response<BooksDetails>

}