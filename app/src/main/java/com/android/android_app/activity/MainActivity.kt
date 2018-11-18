package com.android.android_app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.android.android_app.Fragments.Main.*
import com.android.android_app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cartFragment : Fragment = CartFragment()
        val favoritesFragment : Fragment = FavoritesFragment()
        val foodsFragment : Fragment = FoodsFragment()
        val mainFragment : Fragment = MainFragment()
        val settingsFragment : Fragment = SettingsFragment()


        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction().replace(R.id.frame_layout, mainFragment)
        //transaction.replace(R.id.frame_layout, selectedFragment)
        transaction.commit()

/*
        var selectedFragment: Fragment? = null

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_fragment_foods -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                }
                R.id.action_fragment_favorites -> {
                    selectedFragment = HomeFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                }
                R.id.action_fragment_main -> {
                }
                R.id.action_fragment_cart -> {
                }
                R.id.action_fragment_settings -> {
                }
            }
            return@setOnNavigationItemSelectedListener true
        }*/

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
}
