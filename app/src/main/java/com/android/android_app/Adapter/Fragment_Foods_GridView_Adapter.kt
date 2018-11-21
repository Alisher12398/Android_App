package com.android.android_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.android.android_app.R
import com.android.android_app.model.Food_Model
import kotlinx.android.synthetic.main.framgent_food_grid_item.view.*


class Fragment_Foods_GridView_Adapter : BaseAdapter {
    var foodsList = ArrayList<Food_Model>()
    var context: Context? = null

    constructor(context: Context, foodsList: ArrayList<Food_Model>) : super() {
        this.context = context
        this.foodsList = foodsList
    }

    override fun getCount(): Int {
        return foodsList.size
    }

    override fun getItem(position: Int): Any {
        return foodsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food = this.foodsList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var foodView = inflator.inflate(R.layout.framgent_food_grid_item, null)
        foodView.imgFood.setImageResource(food.image!!)
        foodView.tvName.text = food.name!!

        return foodView
    }
}

/*
class DataAdapter// Конструктор
(internal var mContext: Context, textViewResourceId: Int)// TODO Auto-generated constructor stub
    : ArrayAdapter<String>(mContext, textViewResourceId, mContacts) {

    fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // TODO Auto-generated method stub

        var label: TextView = convertView

        if (convertView == null) {
            convertView = TextView(mContext)
            label = convertView
        }
        label.text = mContacts[position]
        return convertView
    }

    // возвращает содержимое выделенного элемента списка
    override fun getItem(position: Int): String? {
        return mContacts[position]
    }

    companion object {

        private val mContacts = arrayOf("Рыжик", "Барсик", "Мурзик", "Мурка", "Васька", "Полосатик", "Матроскин", "Лизка", "Томосина", "Бегемот", "Чеширский", "Дивуар", "Тигра", "Лаура")
    }

}*/
