package com.halil.roomcompose.ViewModal

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.halil.roomcompose.Db.AppDataBase
import com.halil.roomcompose.Modal.Food
import com.halil.roomcompose.Repository.RoomRepository
import kotlinx.coroutines.launch

class FoodListViewModal (
    context: Context
):ViewModel(){

    private lateinit var repository: RoomRepository

    var foodList=MutableLiveData<List<Food>>()

    init {
        viewModelScope.launch {
            var dao= AppDataBase.getDatabase(context).dao()
            repository=RoomRepository(dao)
            readAllFood()
        }
    }

    fun readAllFood(){
        viewModelScope.launch {
            foodList.postValue(repository.readAllFood())
        }
    }

    fun deleteFood(food: Food){
        viewModelScope.launch {
            repository.deleteFood(food)
            readAllFood()
        }
    }
}