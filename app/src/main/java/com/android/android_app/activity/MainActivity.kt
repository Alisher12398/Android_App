package com.android.android_app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenu
import android.support.design.widget.BottomNavigationView
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

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.selectedItemId = R.id.action_fragment_main


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

        supportFragmentManager.beginTransaction().add(R.id.frame_layout, mainFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, foodsFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, favoritesFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, settingsFragment).commit()

        var active = mainFragment
        var selectedFragment: Fragment? = null
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_fragment_foods -> {
                    supportFragmentManager.beginTransaction().hide(active).show(foodsFragment).commit()
                    active = foodsFragment
                }
                R.id.action_fragment_favorites -> {
                    supportFragmentManager.beginTransaction().hide(active).show(favoritesFragment).commit()
                    active = favoritesFragment
                }
                R.id.action_fragment_main -> {
                    supportFragmentManager.beginTransaction().hide(active).show(mainFragment).commit()
                    active = mainFragment
                }
                R.id.action_fragment_cart -> {
                    supportFragmentManager.beginTransaction().hide(active).show(cartFragment).commit()
                    active = cartFragment
                }
                R.id.action_fragment_settings -> {
                    supportFragmentManager.beginTransaction().hide(active).show(settingsFragment).commit()
                    active = settingsFragment
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
}
