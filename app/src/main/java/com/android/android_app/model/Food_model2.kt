package com.android.android_app.model

class Food_model2 {
    var id_food: String = ""
    var category: String = ""
    var name : String = ""
    var description : String = ""

    constructor(id_food: String, category: String, name: String, description : String){
        this.id_food=id_food
        this.category=category
        this.name=name
        this.description=description
    }
    constructor(){}

}