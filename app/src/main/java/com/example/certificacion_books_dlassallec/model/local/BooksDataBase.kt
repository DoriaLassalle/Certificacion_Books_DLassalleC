package com.example.certificacion_books_dlassallec.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.certificacion_books_dlassallec.model.Books
import com.example.certificacion_books_dlassallec.model.BooksDetails


@Database(entities = [Books::class, BooksDetails::class], version = 1)
abstract class BooksDataBase: RoomDatabase() {

    abstract  fun booksDao():BooksDao

    companion object {

        @Volatile
        private var INSTANCE: BooksDataBase? = null

        fun getDatabase(context: Context): BooksDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDataBase::class.java,
                    "books_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}