package com.android.android_app.Fragments.Main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.android.android_app.Adapter.RecyclerViewGridAdapter
import com.android.android_app.R
import com.android.android_app.model.Food_Model


class FoodsFragment : Fragment() {

    var foodsList = ArrayList<Food_Model>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_foods, container, false)

        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test2"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))
        foodsList.add(Food_Model("Test"))


        var adapter2 = RecyclerViewGridAdapter(foodsList)

        val recycler = view.findViewById(R.id.recyclerView) as RecyclerView

        recycler.adapter = adapter2

        recycler.layoutManager = GridLayoutManager(activity, 2)

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
