package com.android.android_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.android.android_app.activity.MainActivity
import com.android.android_app.model.Category_model
import com.android.android_app.model.Food_model2

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        val DATABASE_NAME = "21"
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
                    table_products_id_product + " VARCHAR PRIMARY KEY NOT NULL, " +
                    table_products_name + " VARCHAR " +
                    ");"
            )
        db.execSQL(sql_create_table_products)

        val sql_create_table_category = (
            "CREATE TABLE " +
                    table_category + "(" +
                    table_category_id_category + " VARCHAR PRIMARY KEY NOT NULL, " +
                    table_category_name + " VARCHAR " +
                    ");"
            )
        db.execSQL(sql_create_table_category)

        val sql_create_table_foods = (
            "CREATE TABLE " +
                    table_foods + "(" +
                    table_foods_id_food + " VARCHAR PRIMARY KEY NOT NULL, " +
                    table_foods_id_category_f + " VARCHAR," +
                    table_foods_name + " VARCHAR, " +
                    table_foods_description + " VARCHAR, " +
                    " FOREIGN KEY ("+table_foods_id_category_f+") REFERENCES "+table_category+"("+table_category_id_category+") ON UPDATE CASCADE " +
                    ");"
            )
        db.execSQL(sql_create_table_foods)

        val sql_create_table_recipes = (
            "CREATE TABLE " +
                    table_recipes + "(" +
                    table_recipes_id_recipe + " VARCHAR PRIMARY KEY NOT NULL, " +
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
                    table_users_user_uid + " VARCHAR PRIMARY KEY NOT NULL," +
                    table_users_user_email + " VARCHAR" +
                    ");"
            )
        db.execSQL(sql_create_table_users)

        val sql_create_table_cart = (
            "CREATE TABLE " +
                    table_cart + "(" +
                    table_cart_id_cart + " VARCHAR PRIMARY KEY NOT NULL, " +
                    table_cart_id_user_f + " INTEGER," +
                    table_cart_id_product_f + " INTEGER," +
                    " FOREIGN KEY ("+table_cart_id_user_f+") REFERENCES "+table_users+"("+table_users_user_uid+")," +
                    " FOREIGN KEY ("+table_cart_id_product_f+") REFERENCES "+table_products+"("+table_products_id_product+")" +
                    ");"
            )
        db.execSQL(sql_create_table_cart)

        val sql_create_table_favorites = (
            "CREATE TABLE " +
                    table_favorites + "(" +
                    table_favorites_id_favorite + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    table_favorites_id_user_f + " VARCHAR," +
                    table_favorites_id_food_f + " VARCHAR," +
                    " FOREIGN KEY ("+table_favorites_id_user_f+") REFERENCES "+table_users+"("+table_users_user_uid+")," +
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

    fun checkestlivproducts(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_product FROM products WHERE id_product LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameinproducts(id : String, name : String){
        val db = writableDatabase
        val sql = "UPDATE products SET name = '$name' WHERE id_product = '$id';"
        db.execSQL(sql)
    }

    fun checkproductidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_product FROM products;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
                do {
                    current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_product")))
                } while (cursor.moveToNext())
            }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM products WHERE id_product = '$a';"
                db.execSQL(sql)
            }
        }
    }



    fun checkestlivcategory(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_category FROM category WHERE id_category LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameincategory(id : String, name : String){
        val db = writableDatabase
        val sql = "UPDATE category SET name = '$name' WHERE id_category = '$id';"
        db.execSQL(sql)
    }

    fun checkcategoryidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_category FROM category;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
            do {
                current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_category")))
            } while (cursor.moveToNext())
        }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM category WHERE id_category = '$a';"
                db.execSQL(sql)
            }
        }
    }







    fun checkestlivfoods(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_food FROM foods WHERE id_food LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameinfoods(id: String, category: String, name: String, description : String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("id_category", category)
        cv.put("name", name)
        cv.put("description", description)
        //db.update("foods", cv, "id_food = ?", null )
    }

    fun checkfoodsidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_category FROM category;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
            do {
                current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_category")))
            } while (cursor.moveToNext())
        }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM category WHERE id_category = '$a';"
                db.execSQL(sql)
            }
        }
    }


    fun parceDBtoListfoods(category : String): List<Food_model2> {
        val foodList = ArrayList<Food_model2>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM foods WHERE id_category='$category'"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Food_model2()
                    food.id_food = cursor.getString(cursor.getColumnIndex("id_food"))
                    food.category = cursor.getString(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    food.description = cursor.getString(cursor.getColumnIndex("description"))
                    foodList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return foodList
    }


    fun parceDBtoListfavorites(uid: String): List<Food_model2> {
        val foodList = ArrayList<Food_model2>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM favorites WHERE id_user = '$uid';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Food_model2()
                    food.id_food = cursor.getString(cursor.getColumnIndex("id_food"))
                    food.category = cursor.getString(cursor.getColumnIndex("id_user"))

                    val selectQuery2 = "SELECT name FROM foods WHERE id_food = '${cursor.getString(cursor.getColumnIndex("id_food"))}';"
                    val cursor2 = db.rawQuery(selectQuery2, null)
                    cursor2.moveToFirst()

                    food.name=cursor2.getString(cursor2.getColumnIndex("name"))
                    foodList.add(food)
                } while (cursor.moveToNext())
            }
        }

        cursor.close()

        return foodList
    }



    fun parceDBtoListcategory(): List<Category_model> {
        val categoryList = ArrayList<Category_model>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM category"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Category_model()
                    food.id_category = cursor.getString(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    categoryList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return categoryList
    }

    fun parceDBtoListfood3(id : String): Food_model2 {

        val db = writableDatabase
        val selectQuery = "SELECT * FROM foods WHERE id_food='$id'"
        val cursor = db.rawQuery(selectQuery, null)

        cursor.moveToFirst()

        val id_food = cursor.getString(cursor.getColumnIndex("id_food"))
        val category = cursor.getString(cursor.getColumnIndex("id_category"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val description = cursor.getString(cursor.getColumnIndex("description"))
        val food = Food_model2(id_food, category, name, description)

        cursor.close()
        return food
    }







    fun checkestlivfavorites(id_food : String, id_user : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_food FROM favorites WHERE id_food = '$id_food' AND id_user = '$id_user';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }
/*
    fun replacenameinfavorites(id_food : String, id_user : String){
        val db = writableDatabase
        val sql = "UPDATE category SET name = '$name' WHERE id_category = '$id';"
        db.execSQL(sql)
    }
    */


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