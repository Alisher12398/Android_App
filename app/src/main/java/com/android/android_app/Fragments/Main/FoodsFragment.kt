package com.android.android_app.Fragments.Main


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.android.android_app.Adapter.RecyclerTouchListener
import com.android.android_app.Adapter.RecyclerViewGridAdapter2
import com.android.android_app.R
import com.android.android_app.model.Food_Model


class FoodsFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        val foodsList = ArrayList<Food_Model>()

        val a : String = "Test"
        /*foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))
        foodsList.add(Food_Model(a, R.drawable.i1))*/

        for (i in 1..10){
            val food = Food_Model()
            food.text="Test$i"
            food.image=R.drawable.i1
            foodsList.add(food)

        }


        val adapter = RecyclerViewGridAdapter2(foodsList)

        val recycler: RecyclerView = view.findViewById(R.id.recycle_view_fragment_foods)

        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter

        recycler.addOnItemTouchListener(RecyclerTouchListener(getActivity()!!.getApplicationContext(), recycler, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(activity, "Click : $position", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(view: View?, position: Int) {
                Toast.makeText(activity, "LongPress : $position", Toast.LENGTH_SHORT).show()
            }
        }))

        return view
    }



   /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_search2 ){
            Toast.makeText(activity, "Text2", Toast.LENGTH_SHORT).show()

        }
       if (item?.itemId == R.id.action_search3 ){
           Toast.makeText(activity, "Text4", Toast.LENGTH_SHORT).show()

       }
       if (item?.itemId == R.id.action_search4 ){
           Toast.makeText(activity, "Text4", Toast.LENGTH_SHORT).show()

       }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu_2, menu)
        //(activity as AppCompatActivity).menuInflater.inflate(R.menu.toolbar_menu_2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }*/

}
