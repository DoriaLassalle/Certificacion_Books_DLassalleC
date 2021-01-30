package com.example.certificacion_books_dlassallec

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.certificacion_books_dlassallec.model.Books
import com.example.certificacion_books_dlassallec.viewmodel.BooksViewModel

class FirstFragment : Fragment(), BooksAdapter.PassBookData {

    private val myViewModel: BooksViewModel by activityViewModels()
    private lateinit var myAdapter:BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter= BooksAdapter(this)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val booksListRecycler=view.findViewById<RecyclerView>(R.id.recycler)
        booksListRecycler.layoutManager=LinearLayoutManager(context)
        booksListRecycler.adapter=myAdapter

        myViewModel.allBooks.observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("libros", it.toString())
                myAdapter.updateAdapter(it)

            }
        })




    }

    override fun passBookInfo(book: Books) {
        val bookSelectedId=book.id

        myViewModel.bookSelected(bookSelectedId)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}