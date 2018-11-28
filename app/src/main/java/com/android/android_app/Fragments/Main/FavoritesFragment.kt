package com.android.android_app.Fragments.Main


import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.android_app.DBHelper

import com.android.android_app.R
import com.android.android_app.activity.MainActivity
import com.android.android_app.model.Food_Model
import com.android.android_app.model.Food_model2
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_favorites.*
import java.util.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favorites, container, false)

        val testtext : TextView = view.findViewById(R.id.testtext2)

        val dbref = FirebaseDatabase.getInstance().reference
        val products_ref = dbref.child("products")
        val category_ref = dbref.child("category")
        val foods22_ref = dbref.child("foods")
        var i=0
        val testlist = ArrayList<String>()

        /*foods22_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                testlist.clear()
                val db = DBHelper(activity as MainActivity)
                db.deletefromtable("foods")
                //val td = dataSnapshot.value as HashMap<String, Boolean>


                for (postSnapshot in dataSnapshot.children) {
                    val food : Food_model2 = postSnapshot.getValue(Food_model2::class.java)!!

                    val sqlitedatabase = db.writableDatabase
                    val insertValues = ContentValues()

                    val idcategory : Int = db.getcategotyid(food.category)
                    insertValues.put("id_category", idcategory)
                    insertValues.put("name", food.name)
                    insertValues.put("description", food.description)
                    sqlitedatabase.insert("foods", null, insertValues)
                    sqlitedatabase.close()
                    testtext.text="${food.name} \n ${food.description} \n ${food.category} "

                    *//*for ((key, value) in td) {
                        testlist.add(key)
                        val sqlitedatabase = db.writableDatabase
                        val insertValues = ContentValues()
                        insertValues.put("name", key)
                        sqlitedatabase.insert("category", null, insertValues)
                        sqlitedatabase.close()
                    }

                    foodsList.add(Food_Model(food.text, food.image))*//*
                }


                *//*for ((key, value) in td) {
                    testlist.add(key)
                    val sqlitedatabase = db.writableDatabase
                    val insertValues = ContentValues()
                    insertValues.put("name", key)
                    sqlitedatabase.insert("category", null, insertValues)
                    sqlitedatabase.close()
                }
                testtext.text = testlist.size.toString()*//*
            }


        })

        category_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                testlist.clear()
                val db = DBHelper(activity as MainActivity)
                db.deletefromtable("category")
                val td = dataSnapshot.value as HashMap<String, Boolean>

                for ((key, value) in td) {
                    testlist.add(key)
                    val sqlitedatabase = db.writableDatabase
                    val insertValues = ContentValues()
                    insertValues.put("name", key)
                    sqlitedatabase.insert("category", null, insertValues)
                    sqlitedatabase.close()
                }
                //testtext.text = testlist.size.toString()
            }


        })*/

        products_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               /* testlist.clear()
                val db = DBHelper(activity as MainActivity)
                db.deletefromtable("products")
                val td = dataSnapshot.value as HashMap<String, Boolean>

                for ((key, value) in td) {
                    testlist.add(key)
                    val sqlitedatabase = db.writableDatabase
                    val insertValues = ContentValues()
                    insertValues.put("name", key)
                    sqlitedatabase.insert("products", null, insertValues)
                    sqlitedatabase.close()
                }*/
                val db = DBHelper(activity as MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivproducts(key)){
                        val insertValues = ContentValues()
                        insertValues.put("id_product", key)
                        insertValues.put("name", value)
                        sqlitedatabase.insert("products", null, insertValues)
                        val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()
                    }

                    if (db.checkestlivproducts(key)){
                        db.replacenameinproducts(key, value)
                        Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                }
            }


        })

        return view
    }


}
