package com.halil.roomcompose.ViewModal

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halil.roomcompose.Db.AppDataBase
import com.halil.roomcompose.Modal.Food
import com.halil.roomcompose.Repository.RoomRepository
import kotlinx.coroutines.launch

class FoodAddViewModal(
    context: Context
):ViewModel() {

    private lateinit var repository: RoomRepository

    init {
        viewModelScope.launch {
            val dao=AppDataBase.getDatabase(context).dao()
            repository = RoomRepository(dao)
        }
    }

    fun addFood(food: Food){
        viewModelScope.launch {
            repository.addFood(food)
        }
    }


}