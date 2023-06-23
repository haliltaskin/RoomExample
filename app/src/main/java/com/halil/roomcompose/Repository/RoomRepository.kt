package com.halil.roomcompose.Repository

import com.halil.roomcompose.Dao.FoodDao
import com.halil.roomcompose.Modal.Food

class RoomRepository(private val dao: FoodDao) {

    suspend fun readAllFood() : List<Food>{
        return dao.readAllFood()
    }

    suspend fun addFood(food: Food){
        dao.addFood(food)
    }

    suspend fun deleteFood(food: Food){
        dao.deleteFood(food)
    }
}