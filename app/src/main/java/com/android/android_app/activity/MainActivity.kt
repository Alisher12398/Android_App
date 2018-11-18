package com.android.android_app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.android_app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
