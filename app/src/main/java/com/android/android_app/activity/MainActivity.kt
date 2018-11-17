package com.android.android_app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.android_app.R
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val adapter = PageAdapter(getSupportFragmentManager())
        pager.adapter = adapter*/
        //

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

   /* fun onClick(v: View) {
        fTrans = fragmentManager.beginTransaction()
        when (v.getId()) {
            R.id.btnAdd -> fTrans.add(R.id.frgmCont, frag1)
            R.id.btnRemove -> fTrans.remove(frag1)
            R.id.btnReplace -> fTrans.replace(R.id.frgmCont, frag2)
            else -> {
            }
        }
        if (chbStack.isChecked()) fTrans.addToBackStack(null)
        fTrans.commit()
    }*/
}
