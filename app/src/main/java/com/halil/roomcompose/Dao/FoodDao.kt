package com.halil.roomcompose.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.halil.roomcompose.Modal.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM Food ORDER BY id ASC")
    suspend fun readAllFood():List<Food>

    @Upsert
   suspend fun addFood(food: Food)

   @Delete
   suspend fun deleteFood(food:Food)
}