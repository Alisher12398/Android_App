package com.android.android_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.android_app.R
import com.android.android_app.model.Food_Model
import kotlinx.android.synthetic.main.recycler_view_item.view.*


class RecyclerViewGridAdapter(var persons:List<Food_Model>):RecyclerView.Adapter<RecyclerViewGridAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = persons[position].text
       // holder.date.text = persons[position].text
        //holder.image.setImageResource(persons[position].getImageUrl())
    }

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(holder.context)
        val view = inflater.inflate(R.layout.recycler_view_item, holder, false)
        return ViewHolder(view)
    }
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.primary_text
        /*var date: TextView  = itemView.drawable
        var image: ImageView = itemView.media_image*/

    }
}