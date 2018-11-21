package com.android.android_app.Fragments.Main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.android.android_app.R
import android.support.v7.app.AppCompatActivity



class FoodsFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       /* val toolbar = view?.findViewById<Toolbar>(R.id.toolbar_foods_fragment)
        setHasOptionsMenu(true)*/

        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        //val toolbar = activity?.findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar_main_activity)

       // toolbar?.menu?.clear()
        //(activity as AppCompatActivity).supportActionBar?.hide()
        //(activity as AppCompatActivity).setSupportActionBar(toolbar)
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
