package com.example.certificacion_books_dlassallec.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.certificacion_books_dlassallec.model.local.BooksDao
import com.example.certificacion_books_dlassallec.model.remote.BooksRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksRepository(private val myBooksDao: BooksDao) {

    private val myRetrofit=BooksRetrofitClient.retrofitInstance()

    val booksList=myBooksDao.getBooksList()

    fun getBooksDetails(id: Int): LiveData<BooksDetails>{
        return myBooksDao.getBooksDetails(id)
    }

    fun getBooksListFromApi()= CoroutineScope(Dispatchers.IO).launch {

        val service= kotlin.runCatching { myRetrofit.fetchBooksList() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    myBooksDao.insertBooksList(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }


    }

    fun getBooksDetailsFromApi(id: Int)= CoroutineScope(Dispatchers.IO).launch {

        val service= kotlin.runCatching { myRetrofit.fetchBookDetails(id)}
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    myBooksDao.insertBooksDetails(it)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())

        }

    }
}