package com.example.certificacion_books_dlassallec

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.certificacion_books_dlassallec.model.Books

class BooksAdapter(val callback: FirstFragment): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(){

   private var booksList= emptyList<Books>()
   fun updateAdapter(mylist:List<Books>){
       booksList=mylist
       notifyDataSetChanged()
   }

    inner class BooksViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        val image=itemview.findViewById<ImageView>(R.id.imgRv)
        val tittle=itemview.findViewById<TextView>(R.id.tvTitleRv)
        val author=itemview.findViewById<TextView>(R.id.tvAuthorRv)
        val country=itemview.findViewById<TextView>(R.id.tvCountryRv)
        val language=itemview.findViewById<TextView>(R.id.tvLanguageRv)
        val click= itemview.setOnClickListener {
            callback.passBookInfo(booksList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.booklist_item, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {

        val img=booksList[position].imageLink
        Glide.with(holder.itemView.context).load(img).into(holder.image)
        holder.tittle.text=booksList[position].title
        holder.author.text=booksList[position].author
        holder.country.text=booksList[position].country
        holder.language.text=booksList[position].language

    }

    override fun getItemCount()= booksList.size


    interface PassBookData{
        fun passBookInfo(book: Books)
    }


}