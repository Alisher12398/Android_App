package com.android.android_app.Fragments.Main


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.android.android_app.Adapter.RecyclerTouchListener
import com.android.android_app.Adapter.RecyclerViewGridAdapter2
import com.android.android_app.R
import com.android.android_app.model.Food_Model
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class FoodsFragment : Fragment() {



    var database = FirebaseDatabase.getInstance()
    var myRef = database.reference
    var reference_category = myRef.child("category")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        val testtext : TextView = view.findViewById(R.id.testtext)
        val foodsList = ArrayList<Food_Model>()

        val testlist = ArrayList<String>()
        val hm : HashMap<String, Boolean> = HashMap()
        hm.put("Key1", true)
        hm.put("Key2", true)
        hm.put("Key3", false)
        var t=1
        reference_category.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val td = dataSnapshot.value as HashMap<String, Objects>
                for ((key, value) in td) {
                    testlist.add(key)
                }
                testtext.text= testlist.size.toString()
                /*val td = dataSnapshot.value as HashMap<String, Boolean>

                val values  = td.values

                for ((key, value) in td) {
                    testlist.add(key)
                }

                //testtext.text = values.size.toString()
                testtext.text= testlist.size.toString()*/


            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

        //testtext.text =testlist.size.toString()
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


        /*val adapter = RecyclerViewGridAdapter2(foodsList)

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
        }))*/

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
