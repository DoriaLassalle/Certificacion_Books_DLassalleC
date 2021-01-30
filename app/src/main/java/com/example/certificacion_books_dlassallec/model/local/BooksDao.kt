package com.example.certificacion_books_dlassallec.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.certificacion_books_dlassallec.model.Books
import com.example.certificacion_books_dlassallec.model.BooksDetails


@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooksList(list: List<Books>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooksDetails(details: BooksDetails)


    @Query("SELECT * FROM books")
    fun getBooksList(): LiveData<List<Books>>

    @Query("SELECT * FROM books_list WHERE id=:id")
    fun getBooksDetails(id: Int): LiveData<BooksDetails>

}