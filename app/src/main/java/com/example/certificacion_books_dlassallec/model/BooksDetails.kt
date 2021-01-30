package com.example.certificacion_books_dlassallec.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books_list")
data class BooksDetails(
    @SerializedName("author")
    val author: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("delivery")
    val delivery: Boolean,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("imageLink")
    val imageLink: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("lastPrice")
    val lastPrice: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)