package com.android.android_app.Fragments.Main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.android_app.Adapter.RecyclerTouchListener
import com.android.android_app.Adapter.RecyclerViewGridAdapter3
import com.android.android_app.DBHelper

import com.android.android_app.R
import com.android.android_app.activity.MainActivity

class FoodsFragment_2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_foods_fragment_2, container, false)


        val database = DBHelper(activity as MainActivity)
        val db = database.writableDatabase
        val choosed_category = (activity as MainActivity).getchoosedCategory()


        val foods_list = database.parceDBtoListfoods(choosed_category)

        Toast.makeText((activity as MainActivity), "${foods_list.size} $choosed_category", Toast.LENGTH_LONG).show()
        val adapter = RecyclerViewGridAdapter3(foods_list)

        val recycler: RecyclerView = view.findViewById(R.id.recycle_view_fragment_foods_2)

        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter

        recycler.addOnItemTouchListener(RecyclerTouchListener(activity!!.applicationContext, recycler, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val id = adapter.getId(position)
                (activity as MainActivity).setchoosedCategory(id)
                (activity as MainActivity).changeToFragment_3()
            }

            override fun onLongClick(view: View?, position: Int) {
                Toast.makeText(activity, "LongPress : $position", Toast.LENGTH_SHORT).show()
            }
        }))


        return view
    }


}
