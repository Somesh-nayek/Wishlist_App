package com.example.my_wishlist_app

import android.app.Application

class WishListApp:Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}