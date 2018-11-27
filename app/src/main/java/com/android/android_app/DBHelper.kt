package com.android.android_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        val DATABASE_NAME = "omegala1_kbtu_android"
        val TABLE_NAME_1 = "products"
        val TABLE_NAME_2 = "cart"
        val TABLE_NAME_3 = "category"
        val TABLE_NAME_4 = "foods"
        val TABLE_NAME_5 = "recipes"

        val table_products= "products"
        val table_products_id_product = "id_product"
        val table_products_name = "name"

        val table_category= "category"
        val table_category_id_category= "id_category"
        val table_category_name = "name"

        val table_foods= "foods"
        val table_foods_id_food= "id_food"
        val table_foods_id_category_f= "id_category"
        val table_foods_name = "name"
        val table_foods_description = "description"

        val table_recipes= "recipes"
        val table_recipes_id_recipe= "id_recipe"
        val table_recipes_id_food_f= "id_food"
        val table_recipes_id_product_f= "id_product"
        val table_recipes_amount = "amount"

        val table_cart= "cart"
        val table_cart_id_cart= "id_cart"
        val table_cart_id_user_f= "id_user"
        val table_cart_id_product_f = "id_product"

        val table_favorites= "favorites"
        val table_favorites_id_favorite= "id_favorite"
        val table_favorites_id_user_f= "id_user"
        val table_favorites_id_food_f= "id_food"

        val table_users= "users"
        val table_users_user_id = "user_id"
        val table_users_user_uid = "user_uid"
        val table_users_user_email = "email"

        const val DATABASE_VERSION = 1
    }

/*
* val sql = (
                "CREATE TABLE " +
                 TABLE_NAME + "(" +
                 COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_NAME + " VARCHAR, " +
                 COLUMN_STATUS + " TINYINT);"
                )*/
    override fun onCreate(db: SQLiteDatabase) {
        val sql_create_table_products = (
            "CREATE TABLE " +
                    table_products + "(" +
                    table_products_id_product + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_products_name + " VARCHAR " +
                    ");"
            )
        db.execSQL(sql_create_table_products)

        val sql_create_table_category = (
            "CREATE TABLE " +
                    table_category + "(" +
                    table_category_id_category + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_category_name + " VARCHAR " +
                    ");"
            )
        db.execSQL(sql_create_table_category)

        val sql_create_table_foods = (
            "CREATE TABLE " +
                    table_foods + "(" +
                    table_foods_id_food + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_foods_id_category_f + " INTEGER," +
                    table_foods_name + " VARCHAR, " +
                    table_foods_description + " VARCHAR, " +
                    " FOREIGN KEY ("+table_foods_id_category_f+") REFERENCES "+table_category+"("+table_category_id_category+") ON UPDATE CASCADE " +
                    ");"
            )
        db.execSQL(sql_create_table_foods)

        val sql_create_table_recipes = (
            "CREATE TABLE " +
                    table_recipes + "(" +
                    table_recipes_id_recipe + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_recipes_id_food_f + " INTEGER," +
                    table_recipes_id_product_f + " INTEGER," +
                    table_recipes_amount + " INTEGER, " +
                    " FOREIGN KEY ("+table_recipes_id_food_f+") REFERENCES "+table_foods+"("+table_foods_id_food+")," +
                    " FOREIGN KEY ("+table_recipes_id_product_f+") REFERENCES "+table_products+"("+table_products_id_product+")" +
                    ");"
            )
        db.execSQL(sql_create_table_recipes)

        val sql_create_table_users = (
            "CREATE TABLE " +
                    table_users + "(" +
                    table_users_user_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_users_user_uid + " VARCHAR," +
                    table_users_user_email + " VARCHAR" +
                    ");"
            )
        db.execSQL(sql_create_table_users)

        val sql_create_table_cart = (
            "CREATE TABLE " +
                    table_cart + "(" +
                    table_cart_id_cart + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_cart_id_user_f + " INTEGER," +
                    table_cart_id_product_f + " INTEGER," +
                    " FOREIGN KEY ("+table_cart_id_user_f+") REFERENCES "+table_users+"("+table_users_user_id+")," +
                    " FOREIGN KEY ("+table_cart_id_product_f+") REFERENCES "+table_products+"("+table_products_id_product+")" +
                    ");"
            )
        db.execSQL(sql_create_table_cart)

        val sql_create_table_favorites = (
            "CREATE TABLE " +
                    table_favorites + "(" +
                    table_favorites_id_favorite + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_favorites_id_user_f + " INTEGER," +
                    table_favorites_id_food_f + " INTEGER," +
                    " FOREIGN KEY ("+table_favorites_id_user_f+") REFERENCES "+table_users+"("+table_users_user_id+")," +
                    " FOREIGN KEY ("+table_favorites_id_food_f+") REFERENCES "+table_products+"("+table_foods_id_food+")" +
                    ");"
            )
        db.execSQL(sql_create_table_favorites)

    }

    fun deletefromtable(name : String){
        val db = writableDatabase
        val sql = "DELETE FROM $name;"
        db.execSQL(sql)
    }

    fun getcategotyid(name : String) : Int{
        val db = writableDatabase
        val sql = "SELECT * FROM category WHERE name LIKE '$name';"
        val c : Cursor = db.rawQuery(sql, null)
        c.moveToFirst()
        var s : Int = 2

        s = c.getInt(c.getColumnIndex("id_category"))
        return s
    }




/*
* val db = DBHelper(this)

        db.readableDatabase

        test_textview_1.text = "test"

        foods = db.parceDBtoList()
        test_textview_1.text = "id_food: " + foods[0].id_foods.toString()
        test_textview_2.text = "\nid_product: " +foods[0].id_category.toString()
        test_textview_3.text = "\nfood_name: " +foods[0].name
        test_textview_4.text = "\nfood_desc: " +foods[0].description
*
* */
    /*fun parceDBtoList(): List<FoodsModel> {
        val newsList = ArrayList<FoodsModel>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM food"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = FoodsModel()
                    food.id_foods = cursor.getInt(cursor.getColumnIndex("_ID"))
                    food.id_category = cursor.getInt(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    food.description = cursor.getString(cursor.getColumnIndex("description"))
                    newsList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return newsList
    }*/


    //upgrading the database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS Persons"
        db.execSQL(sql)
        onCreate(db)
    }

    /*
    * This method is taking two arguments
    * first one is the name that is to be saved
    * second one is the status
    * 0 means the name is synced with the server
    * 1 means the name is not synced with the server
    * */
   /* fun addName(name: String, status: Int): Boolean {
        val db = this.getWritableDatabase()
        val contentValues = ContentValues()

        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_STATUS, status)


        db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return true
    }

    *//*
    * This method taking two arguments
    * first one is the id of the name for which
    * we have to update the sync status
    * and the second one is the status that will be changed
    * *//*
    fun updateNameStatus(id: Int, status: Int): Boolean {
        val db = this.getWritableDatabase()
        val contentValues = ContentValues()
        contentValues.put(COLUMN_STATUS, status)
        db.update(TABLE_NAME, contentValues, "$COLUMN_ID=$id", null)
        db.close()
        return true
    }

    *//*
    * this method will give us all the name stored in sqlite
    * *//*
    fun getNames(): Cursor {
        val db = this.getReadableDatabase()
        val sql = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID ASC;"
        return db.rawQuery(sql, null)
    }

    *//*
    * this method is for getting all the unsynced name
    * so that we can sync it with database
    * *//*
    fun getUnsyncedNames(): Cursor {
        val db = this.getReadableDatabase()
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_STATUS = 0;"
        return db.rawQuery(sql, null)
    }*/
}