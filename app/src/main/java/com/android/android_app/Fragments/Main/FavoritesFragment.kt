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
import kotlin.collections.ArrayList

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favorites, container, false)

        val testtext : TextView = view.findViewById(R.id.testtext2)

        /*val dbref = FirebaseDatabase.getInstance().reference
        val products_ref = dbref.child("products")
        val category_ref = dbref.child("category")
        val foods22_ref = dbref.child("foods")
        var i=0
        val testlist = ArrayList<String>()





        val current_food_ids = ArrayList<String>()
        foods22_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val db = DBHelper(activity as MainActivity)


                for (postSnapshot in dataSnapshot.children) {
                    val food : Food_model2 = postSnapshot.getValue(Food_model2::class.java)!!

                    val sqlitedatabase = db.writableDatabase

                    //if (!db.checkestlivfoods(food.id_food)){
                        val insertValues = ContentValues()
                        insertValues.put("id_food", food.id_food)
                        insertValues.put("id_category", food.category)
                        insertValues.put("name", food.name)
                        insertValues.put("description", food.description)
                        sqlitedatabase.insert("foods", null, insertValues)
                        *//*val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()*//*
                    //}

                    *//*if (db.checkestlivfoods(food.id_food)){
                        db.replacenameinfoods(food.id_food, food.category, food.name, food.description)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()*//*

                }
            }


        })




        val current_categories = ArrayList<String>()
        category_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               *//* testlist.clear()
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
                *//*
                current_categories.clear()

                val db = DBHelper(activity as MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivcategory(key)){
                        val insertValues = ContentValues()
                        insertValues.put("id_category", key)
                        insertValues.put("name", value)
                        sqlitedatabase.insert("category", null, insertValues)
                        *//*val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()*//*
                    }

                    if (db.checkestlivcategory(key)){
                        db.replacenameincategory(key, value)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                    current_categories.add(key)

                }
                db.checkcategoryidsindb(current_categories)
                *//*val i2 =db.checkproductidsindb(current_ids)
                val toast = Toast.makeText(activity as MainActivity, "$i2 ${current_ids.size}", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 300)
                toast.show()*//*

            }


        })
        val current_ids = ArrayList<String>()
        products_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               current_ids.clear()

                *//* testlist.clear()
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
                }*//*
                val db = DBHelper(activity as MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivproducts(key)){
                        val insertValues = ContentValues()
                        insertValues.put("id_product", key)
                        insertValues.put("name", value)
                        sqlitedatabase.insert("products", null, insertValues)
                        *//*val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()*//*
                    }

                    if (db.checkestlivproducts(key)){
                        db.replacenameinproducts(key, value)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                    current_ids.add(key)

                }
                db.checkproductidsindb(current_ids)
                *//*val i2 =db.checkproductidsindb(current_ids)
                val toast = Toast.makeText(activity as MainActivity, "$i2 ${current_ids.size}", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 300)
                toast.show()*//*
            }


        })*/

        return view
    }


}
