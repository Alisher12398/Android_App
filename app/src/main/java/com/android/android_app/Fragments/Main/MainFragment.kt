package com.android.android_app.Fragments.Main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.android.android_app.R
import com.android.android_app.activity.MainActivity

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val textview_fragment_main_title : TextView = view.findViewById(R.id.textview_fragment_main_title)
        val textview_fragment_main_description : TextView = view.findViewById(R.id.textview_fragment_main_description)
        val imageview_fragment_main_image : ImageView = view.findViewById(R.id.imageview_fragment_main_image)

        val current_food = (activity as MainActivity).getcurrent_food()

        textview_fragment_main_title.text = current_food.name
        textview_fragment_main_description.text = current_food.description
        if (current_food.name.isNotEmpty()) {
            //imageview_fragment_main_image.setImageResource(R.drawable.category3)
            when (current_food.id_food) {
                "id_food_1" -> imageview_fragment_main_image.setImageResource(R.drawable.food1)
                "id_food_2" -> imageview_fragment_main_image.setImageResource(R.drawable.category1)
                "id_food_3" -> imageview_fragment_main_image.setImageResource(R.drawable.food1)
                "id_food_4" -> imageview_fragment_main_image.setImageResource(R.drawable.category1)
                "id_food_5" -> imageview_fragment_main_image.setImageResource(R.drawable.food2)
                "id_food_6" -> imageview_fragment_main_image.setImageResource(R.drawable.category2)
                "id_food_7" -> imageview_fragment_main_image.setImageResource(R.drawable.food2)
                "id_food_8" -> imageview_fragment_main_image.setImageResource(R.drawable.category2)
                "id_food_9" -> imageview_fragment_main_image.setImageResource(R.drawable.food3)
                "id_food_10" -> imageview_fragment_main_image.setImageResource(R.drawable.category3)
                "id_food_11" -> imageview_fragment_main_image.setImageResource(R.drawable.food3)
                "id_food_12" -> imageview_fragment_main_image.setImageResource(R.drawable.category3)
            }

        }
        return view
    }


}
