package com.example.my_wishlist_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_wishlist_app.data.Wish
import com.example.my_wishlist_app.data.wishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: wishRepository = Graph.wishRepository
):ViewModel() {

    var wishtitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishtitleChanges(newString: String){
        wishtitleState=newString

    }

    fun onWishDescriptionChanges(newDescription: String){
        wishDescriptionState=newDescription

    }

    lateinit var getAllWish: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWish=wishRepository.getWish()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.addWish(wish=wish)
        }
    }

    fun getAllwishById(id:Long):Flow<Wish>{
        return wishRepository.getAWishById(id)
    }

    fun UpdateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.updateWish(wish=wish)
        }
    }

    fun DeleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deletewish(wish)
        }
    }
}