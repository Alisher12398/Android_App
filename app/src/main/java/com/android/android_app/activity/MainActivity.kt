package com.android.android_app.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.android.android_app.DBHelper
import com.android.android_app.Fragments.Main.*
import com.android.android_app.R
import com.android.android_app.model.Food_Model
import com.android.android_app.model.Food_model2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.HashMap


class MainActivity : AppCompatActivity() {

    val cartFragment : Fragment = CartFragment()
    val favoritesFragment : Fragment = FavoritesFragment()
    val foodsFragment : Fragment = FoodsFragment()
    val mainFragment : Fragment = MainFragment()
    val settingsFragment : Fragment = SettingsFragment()
    val foodsFragment_2 : Fragment = FoodsFragment_2()
    val foodsFragment_3 : Fragment = FoodsFragment_3()
    var choosedCategory : String = ""
    var active = mainFragment

    fun getchoosedCategory() : String{
        return  choosedCategory
    }

    fun setchoosedCategory(choosedCategory_from_fragment : String){
        choosedCategory = choosedCategory_from_fragment
    }


    fun checkestlivfoods(id : String) : Boolean{
        val db = DBHelper(this@MainActivity)
        val sqlitedatabase = db.writableDatabase

        val sql = "SELECT id_food FROM foods WHERE id_food LIKE '$id';"
        val c : Cursor = sqlitedatabase.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun inserttoDBfoods (){
        val db = DBHelper(this@MainActivity)
        val sqlitedatabase = db.writableDatabase
        for (i in 1..4){

            val insertValues = ContentValues()
            insertValues.put("id_food", "id_food_$i")
            insertValues.put("id_category", "category_id_1")
            insertValues.put("name", "Food $i (1)")
            insertValues.put("description", "Tasty food $i")
            sqlitedatabase.insert("foods", null, insertValues)

        }

        for (i in 5..8){

            val insertValues2 = ContentValues()
            insertValues2.put("id_food", "id_food_$i")
            insertValues2.put("id_category", "category_id_2")
            insertValues2.put("name", "Food $i (2)")
            insertValues2.put("description", "Tasty food $i")
            sqlitedatabase.insert("foods", null, insertValues2)

        }

        for (i in 9..12){

            val insertValues3 = ContentValues()
            insertValues3.put("id_food", "id_food_$i")
            insertValues3.put("id_category", "category_id_3")
            insertValues3.put("name", "Food $i (3)")
            insertValues3.put("description", "Tasty food $i")
            sqlitedatabase.insert("foods", null, insertValues3)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRef = FirebaseDatabase.getInstance().getReference("message")
        val user = FirebaseAuth.getInstance().currentUser
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar_main_activity)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.selectedItemId = R.id.action_fragment_main
        bottom_navigation.disableShiftMode()


        if (!checkestlivfoods("id_food_1")){
            inserttoDBfoods()
        }


        val dbref = FirebaseDatabase.getInstance().reference
        val products_ref = dbref.child("products")
        val favorites_ref = dbref.child("users").child(user!!.uid).child("favorites")
        val category_ref = dbref.child("category")
        val foods22_ref = dbref.child("foods")
        var i=0
        val testlist = ArrayList<String>()








        /*val current_food_ids = ArrayList<String>()
        foods22_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val db = DBHelper(this@MainActivity)


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
*/



        val current_categories = ArrayList<String>()
        category_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                /* testlist.clear()
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
                 */
                current_categories.clear()

                val db = DBHelper(this@MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivcategory(key)){
                        val insertValues = ContentValues()
                        insertValues.put("id_category", key)
                        insertValues.put("name", value)
                        sqlitedatabase.insert("category", null, insertValues)
                        /*val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()*/
                    }

                    if (db.checkestlivcategory(key)){
                        db.replacenameincategory(key, value)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                    current_categories.add(key)

                }
                db.checkcategoryidsindb(current_categories)
                /*val i2 =db.checkproductidsindb(current_ids)
                val toast = Toast.makeText(activity as MainActivity, "$i2 ${current_ids.size}", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 300)
                toast.show()*/

            }


        })









        /*val current_favorites = ArrayList<String>()
        favorites_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                current_favorites.clear()

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
                val db = DBHelper(this@MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivfavorites(value, user.uid)){
                        val insertValues = ContentValues()
                        insertValues.put("id_food", value)
                        insertValues.put("id_user", user.uid)
                        sqlitedatabase.insert("favorites", null, insertValues)

                    }

                    if (db.checkestlivproducts(value)){
                        db.replacenameinproducts(value, user.uid)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                    //current_ids.add(key)

                }
                //db.checkproductidsindb(current_ids)
                *//*val i2 =db.checkproductidsindb(current_ids)
                val toast = Toast.makeText(activity as MainActivity, "$i2 ${current_ids.size}", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 300)
                toast.show()*//*
            }


        })*/

























        val current_ids = ArrayList<String>()
        products_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                current_ids.clear()

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
                val db = DBHelper(this@MainActivity)
                val td = dataSnapshot.value as HashMap<String, String>

                for ((key, value) in td) {
                    val sqlitedatabase = db.writableDatabase

                    if (!db.checkestlivproducts(key)){
                        val insertValues = ContentValues()
                        insertValues.put("id_product", key)
                        insertValues.put("name", value)
                        sqlitedatabase.insert("products", null, insertValues)
                        /*val toast = Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.BOTTOM, 0, 150)
                        toast.show()*/
                    }

                    if (db.checkestlivproducts(key)){
                        db.replacenameinproducts(key, value)
                        //Toast.makeText(activity as MainActivity, "$value $key", Toast.LENGTH_LONG).show()
                    }
                    sqlitedatabase.close()

                    current_ids.add(key)

                }
                db.checkproductidsindb(current_ids)
                /*val i2 =db.checkproductidsindb(current_ids)
                val toast = Toast.makeText(activity as MainActivity, "$i2 ${current_ids.size}", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 300)
                toast.show()*/
            }


        })









        /*val transaction_mainFragment : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, mainFragment)
        val transaction_foodsFragment : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, foodsFragment)
        val transaction_favoritesFragment : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, favoritesFragment)
        val transaction_cartFragment : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, cartFragment)
        val transaction_settingsFragment : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, settingsFragment)*/
        //transaction.replace(R.id.frame_layout, selectedFragment)
        //transaction_mainFragment.commit()

        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.main_activity_fragment_title_main)
        //toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_36)

        supportFragmentManager.beginTransaction().add(R.id.frame_layout, mainFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, foodsFragment).hide(foodsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, favoritesFragment).hide(favoritesFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, cartFragment).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, settingsFragment).hide(settingsFragment).commit()
        supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
        supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_fragment_foods -> {
                    supportFragmentManager.beginTransaction().hide(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().hide(active).show(foodsFragment).commit()
                    active = foodsFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_foods)
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.toolbar_menu)
                    toolbar.setBackgroundColor(resources.getColor(R.color.colorMenu))
                    supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()

                }
                R.id.action_fragment_favorites -> {
                    supportFragmentManager.beginTransaction().hide(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().hide(active).show(favoritesFragment).commit()
                    active = favoritesFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_favorites)
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.toolbar_menu)
                    toolbar.setBackgroundColor(resources.getColor(R.color.colorMenu))
                    supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()
                }
                R.id.action_fragment_main -> {
                    supportFragmentManager.beginTransaction().hide(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().hide(active).show(mainFragment).commit()
                    active = mainFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_main)
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.toolbar_menu)
                    toolbar.setBackgroundColor(resources.getColor(R.color.colorMenu))
                    supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()
                }
                R.id.action_fragment_cart -> {
                    supportFragmentManager.beginTransaction().hide(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().hide(active).show(cartFragment).commit()
                    active = cartFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_cart)
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.toolbar_menu)
                    toolbar.setBackgroundColor(resources.getColor(R.color.colorMenu))
                    supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()
                }
                R.id.action_fragment_settings -> {
                    supportFragmentManager.beginTransaction().hide(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().hide(active).show(settingsFragment).commit()
                    active = settingsFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_settings)
                    toolbar.menu.clear()
                    toolbar.setBackgroundColor(resources.getColor(R.color.black))
                    supportFragmentManager.beginTransaction().remove(foodsFragment_2).commit()
                    supportFragmentManager.beginTransaction().remove(foodsFragment_3).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    var oneOnBackPressed = false
    private var back_pressed: Long = 0

    override fun onBackPressed() {

        /*
        Handler().postDelayed({
        oneOnBackPressed = false
        }, 2000)*/

        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed()
        else Toast.makeText(this, "Нажмите еще раз 'Назад' для выхода", Toast.LENGTH_LONG).show()
        back_pressed = System.currentTimeMillis()

    }

    @SuppressLint("RestrictedApi")
    fun BottomNavigationView.disableShiftMode() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {

        } catch (e: IllegalStateException) {

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.refresh_icon){
            if (active == cartFragment) Toast.makeText(this, "Main", Toast.LENGTH_LONG).show()
            supportFragmentManager.beginTransaction().detach(active).attach(active).commit()
        }
        return super.onOptionsItemSelected(item)
    }

    fun changeToFragment_2(){
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, foodsFragment_2).hide(foodsFragment_2).commit()
        supportFragmentManager.beginTransaction().hide(foodsFragment).show(foodsFragment_2).commit()
    }
    fun changeToFragment_3(){
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, foodsFragment_3).hide(foodsFragment_3).commit()
        supportFragmentManager.beginTransaction().hide(foodsFragment_2).show(foodsFragment_3).commit()
    }
}
