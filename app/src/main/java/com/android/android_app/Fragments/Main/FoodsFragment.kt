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
import com.android.android_app.DBHelper
import com.android.android_app.R
import com.android.android_app.activity.MainActivity
import com.android.android_app.model.Food_Model
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap





class FoodsFragment : Fragment() {

    var myRef = FirebaseDatabase.getInstance().reference

    var reference_category = myRef.child("category")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        /*reference_category.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                foodsList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val food : Food_Model = postSnapshot.getValue(Food_Model::class.java)!!

                    when(postSnapshot.key){
                        "category1" -> food.image=R.drawable.category1
                        "category2" -> food.image=R.drawable.category2
                        "category3" -> food.image=R.drawable.category3
                    }
                    foodsList.add(Food_Model(food.text, food.image))
                }

            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })*/


        val database = DBHelper(activity as MainActivity)
        val db = database.writableDatabase

        val categoryList = database.parceDBtoListcategory()


        val adapter = RecyclerViewGridAdapter2(categoryList)

        val recycler: RecyclerView = view.findViewById(R.id.recycle_view_fragment_foods)

        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter

        recycler.addOnItemTouchListener(RecyclerTouchListener(activity!!.applicationContext, recycler, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {

                val id = adapter.getId(position)
                (activity as MainActivity).setchoosedCategory(id)
                (activity as MainActivity).changeToFragment_2()
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
