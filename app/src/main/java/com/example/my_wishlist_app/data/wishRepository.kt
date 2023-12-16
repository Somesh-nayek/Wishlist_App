package com.example.my_wishlist_app.data

import kotlinx.coroutines.flow.Flow

class wishRepository(private val wishDao:wishDAO) {

    suspend fun addWish(wish:Wish){
        wishDao.addWish(wish)
    }

    fun getWish(): Flow<List<Wish>> = wishDao.getAllWishes()


    fun getAWishById(Id:Long):Flow<Wish>{
        return wishDao.getAWishbyId(Id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }
    suspend fun deletewish(wish: Wish){
        wishDao.DeleteWish(wish)
    }
}