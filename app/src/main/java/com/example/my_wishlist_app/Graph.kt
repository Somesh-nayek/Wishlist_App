package com.example.my_wishlist_app

import android.content.Context
import androidx.room.Room
import com.example.my_wishlist_app.data.wishDataBase
import com.example.my_wishlist_app.data.wishRepository

object Graph {
    lateinit var dataBase: wishDataBase
    val wishRepository by lazy {
        wishRepository(wishDao = dataBase.wishDao())
    }

    fun provide(context: Context){
        dataBase= Room.databaseBuilder(context,wishDataBase::class.java,"wishlist.db").build()
    }
}