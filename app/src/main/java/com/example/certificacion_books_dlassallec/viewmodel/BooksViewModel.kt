package com.example.certificacion_books_dlassallec.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.certificacion_books_dlassallec.model.Books
import com.example.certificacion_books_dlassallec.model.BooksDetails
import com.example.certificacion_books_dlassallec.model.BooksRepository
import com.example.certificacion_books_dlassallec.model.local.BooksDataBase

class BooksViewModel (application: Application):AndroidViewModel(application){

    private val myRepository: BooksRepository

    val allBooks: LiveData<List<Books>>

    val bookSelection= MutableLiveData<Int>()

    init {
        val myDao= BooksDataBase.getDatabase(application).booksDao()
        myRepository= BooksRepository(myDao)
        allBooks=myRepository.booksList
        myRepository.getBooksListFromApi()
    }

    fun getBooksDetails(id:Int) : LiveData<BooksDetails> {
        myRepository.getBooksDetailsFromApi(id)
        return myRepository.getBooksDetails(id)

    }

    fun bookSelected(id: Int){
        bookSelection.value= id
    }
}