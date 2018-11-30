package com.android.android_app.Fragments.Main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.android_app.DBHelper

import com.android.android_app.R
import com.android.android_app.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FoodsFragment_3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_foods_fragment_3, container, false)

        val imageview_fragment_foods_3_image : ImageView = view.findViewById(R.id.imageview_fragment_foods_3_image)
        val textview_fragment_foods_3_title : TextView = view.findViewById(R.id.textview_fragment_foods_3_title)
        val textview_fragment_foods_3_description : TextView = view.findViewById(R.id.textview_fragment_foods_3_description)
        val button_fragment_foods_3_choose : Button = view.findViewById(R.id.button_fragment_foods_3_choose)

        val myRef= FirebaseDatabase.getInstance().reference
        val user = FirebaseAuth.getInstance().currentUser

        val database = DBHelper(activity as MainActivity)
        val db = database.writableDatabase

        val choosed_food = (activity as MainActivity).getchoosedCategory()

        val choosed_food_object = database.parceDBtoListfood3(choosed_food)

        textview_fragment_foods_3_title.text=choosed_food_object.name
        textview_fragment_foods_3_description.text=choosed_food_object.description
        imageview_fragment_foods_3_image.setImageResource(R.drawable.category1)

        when (choosed_food_object.id_food) {
            "id_food_1" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food1)
            "id_food_2" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category1)
            "id_food_3" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food1)
            "id_food_4" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category1)
            "id_food_5" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food2)
            "id_food_6" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category2)
            "id_food_7" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food2)
            "id_food_8" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category2)
            "id_food_9" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food3)
            "id_food_10" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category3)
            "id_food_11" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.food3)
            "id_food_12" -> imageview_fragment_foods_3_image.setImageResource(R.drawable.category3)
        }
        

        button_fragment_foods_3_choose.setOnClickListener {
            (activity as MainActivity).setcurrent_food(choosed_food_object)
            (activity as MainActivity).changetoFragment_main()
            Toast.makeText((activity as MainActivity), "Choosed", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}
