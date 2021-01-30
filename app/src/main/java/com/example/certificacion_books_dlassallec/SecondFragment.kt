package com.example.certificacion_books_dlassallec

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.certificacion_books_dlassallec.viewmodel.BooksViewModel
import java.lang.Exception


class SecondFragment : Fragment() {

    private val myViewModel: BooksViewModel by activityViewModels()
    private var idBook = 0
    lateinit var bookTitle: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.bookSelection.observe(viewLifecycleOwner, Observer {
            idBook=it

            myViewModel.getBooksDetails(idBook).observe(viewLifecycleOwner, Observer {

                it?.let {

                    bookTitle=it.title

                    val imgBookSel= view.findViewById<ImageView>(R.id.detImage)
                    Glide.with(this).load(it.imageLink).fitCenter().into(imgBookSel)

                    val titleSel=view.findViewById<TextView>(R.id.detTitle)
                    val authorSel=view.findViewById<TextView>(R.id.detAuthor)
                    val countrySel=view.findViewById<TextView>(R.id.detCountry)
                    val yearSel=view.findViewById<TextView>(R.id.detYear)
                    val pagesSel=view.findViewById<TextView>(R.id.detPages)
                    val deliverySel=view.findViewById<TextView>(R.id.detDelivery)
                    val urlSel=view.findViewById<TextView>(R.id.detUrl)
                    val priceSel=view.findViewById<TextView>(R.id.detPrice)
                    val lastPriceSel=view.findViewById<TextView>(R.id.detLastPrice)

                    titleSel.text=it.title
                    authorSel.text=getString(R.string.author)+it.author
                    countrySel.text=getString(R.string.pais)+it.country
                    yearSel.text=getString(R.string.year)+it.year.toString()
                    pagesSel.text=it.pages.toString()+ getString(R.string.paginas)
                    urlSel.text=it.link
                    priceSel.text=getString(R.string.precio)+ it.price.toString()
                    lastPriceSel.text=getString(R.string.last_price)+it.lastPrice.toString()

                    if (it.delivery==true) {
                        deliverySel.text =getString(R.string.delivery_true)
                    }else{
                        deliverySel.text=getString(R.string.delivery_false)
                    }

                }
            })
        })

        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.btnComprar).setOnClickListener {

            val emailAddress=getString(R.string.mail_address)
            val emailSubject=getString(R.string.mail_subject)+bookTitle+getString(R.string.mail_subject2)+idBook
            val emailText=getString(
                R.string.mail_text1)+bookTitle+getString(
                R.string.mail_text2)+idBook+getString(
                R.string.mail_text3)

            createEmail(emailAddress, emailSubject, emailText)
        }
    }

    fun createEmail(emailAddress:String, emailSubject: String, emailText: String){

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
        intent.putExtra(Intent.EXTRA_TEXT, emailText)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }
    }
}