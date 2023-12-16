package com.example.my_wishlist_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class wishDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addWish(wishEntity: Wish)

    @Query("Select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun  updateWish(wishEntity: Wish)

    @Delete
    abstract fun DeleteWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id=:id")
    abstract  fun getAWishbyId(id:Long): Flow<Wish>


}