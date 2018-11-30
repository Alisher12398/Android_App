package com.android.android_app.Fragments.Main


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.android_app.Adapter.RecyclerTouchListener
import com.android.android_app.Adapter.RecyclerViewGridAdapter2
import com.android.android_app.Adapter.RecyclerViewGridAdapter3
import com.android.android_app.DBHelper

import com.android.android_app.R
import com.android.android_app.activity.MainActivity
import com.android.android_app.model.Food_Model
import com.android.android_app.model.Food_model2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_favorites.*
import java.util.*
import kotlin.collections.ArrayList

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favorites, container, false)


        val myRef= FirebaseDatabase.getInstance().reference
        val user = FirebaseAuth.getInstance().currentUser

        val database = DBHelper(activity as MainActivity)
        val db = database.writableDatabase
        val choosed_category = (activity as MainActivity).getchoosedCategory()


        val foods_list = database.parceDBtoListfavorites(user!!.uid)

        val adapter = RecyclerViewGridAdapter3(foods_list)

        val recycler: RecyclerView = view.findViewById(R.id.recycle_view_fragment_favorites)

        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter
        recycler.addOnItemTouchListener(RecyclerTouchListener(activity!!.applicationContext, recycler, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val id = adapter.getId(position)
                (activity as MainActivity).setchoosedCategory(id)
                (activity as MainActivity).changeToFragment_3()
            }

            override fun onLongClick(view: View?, position: Int) {
            }
        }))

        return view
    }


}
