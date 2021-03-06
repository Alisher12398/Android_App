package com.android.android_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.android.android_app.R
import com.android.android_app.model.Food_Model
import com.android.android_app.model.Food_model2
import kotlinx.android.synthetic.main.recycler_view_item.view.*


class RecyclerViewGridAdapter3(var persons:List<Food_model2>):RecyclerView.Adapter<RecyclerViewGridAdapter3.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = persons[position].name
        //holder.image.setImageResource(R.drawable.category2)
        when(persons[position].id_food){
            "id_food_1" ->  holder.image.setImageResource(R.drawable.food1)
            "id_food_2" ->  holder.image.setImageResource(R.drawable.category1)
            "id_food_3" ->  holder.image.setImageResource(R.drawable.food1)
            "id_food_4" ->  holder.image.setImageResource(R.drawable.category1)
            "id_food_5" ->  holder.image.setImageResource(R.drawable.food2)
            "id_food_6" ->  holder.image.setImageResource(R.drawable.category2)
            "id_food_7" ->  holder.image.setImageResource(R.drawable.food2)
            "id_food_8" ->  holder.image.setImageResource(R.drawable.category2)
            "id_food_9" ->  holder.image.setImageResource(R.drawable.food3)
            "id_food_10" ->  holder.image.setImageResource(R.drawable.category3)
            "id_food_11" ->  holder.image.setImageResource(R.drawable.food3)
            "id_food_12" ->  holder.image.setImageResource(R.drawable.category3)
        }
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

    fun getId(position: Int): String {
        return persons[position].id_food
    }


    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.textview_fragment_foods_text
        var image: ImageView = itemView.imageview_fragment_foods_image
        /*var date: TextView  = itemView.drawable
        var image: ImageView = itemView.media_image*/

    }
}