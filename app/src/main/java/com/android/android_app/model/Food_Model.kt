package com.android.android_app.model

class Food_Model{
    var text: String = ""
    var image: Int = 0

    constructor(text: String, image: Int){
        this.text=text
        this.image=image
    }
    constructor(){}

    fun getImageUrl(): Int{
        return image
    }

}