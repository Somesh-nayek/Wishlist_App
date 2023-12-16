package com.example.my_wishlist_app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo(name = "Wish-title")
    val title:String="",
    @ColumnInfo(name="wish-desc")
    val description:String=""
)
object DummyWish{
    val wishList= listOf(
        Wish(title = "$300 million", description = "to get every comfort for others"),
        Wish(title = "a pill to rest in peace", description = "after giving every comfort to everyone")
    )
}