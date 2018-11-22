package com.android.android_app.activity

import android.annotation.SuppressLint
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
import com.android.android_app.Fragments.Main.*
import com.android.android_app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!");








        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.selectedItemId = R.id.action_fragment_main
        bottom_navigation.disableShiftMode()

        //https://www.youtube.com/watch?v=C0D7zzIUBec

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar_main_activity)
        val cartFragment : Fragment = CartFragment()
        val favoritesFragment : Fragment = FavoritesFragment()
        val foodsFragment : Fragment = FoodsFragment()
        val mainFragment : Fragment = MainFragment()
        val settingsFragment : Fragment = SettingsFragment()

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

        var active = mainFragment
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_fragment_foods -> {
                    supportFragmentManager.beginTransaction().hide(active).show(foodsFragment).commit()
                    active = foodsFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_foods)
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.toolbar_menu_2)

                }
                R.id.action_fragment_favorites -> {
                    supportFragmentManager.beginTransaction().hide(active).show(favoritesFragment).commit()
                    active = favoritesFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_favorites)
                }
                R.id.action_fragment_main -> {
                    supportFragmentManager.beginTransaction().hide(active).show(mainFragment).commit()
                    active = mainFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_main)
                }
                R.id.action_fragment_cart -> {
                    supportFragmentManager.beginTransaction().hide(active).show(cartFragment).commit()
                    active = cartFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_cart)
                }
                R.id.action_fragment_settings -> {
                    supportFragmentManager.beginTransaction().hide(active).show(settingsFragment).commit()
                    active = settingsFragment
                    toolbar.setTitle(R.string.main_activity_fragment_title_settings)
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
        if (item?.itemId == R.id.action_search2){
            val toast = Toast.makeText(this, "Text", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 150)
            toast.show()
        }
        return super.onOptionsItemSelected(item)
    }
}
