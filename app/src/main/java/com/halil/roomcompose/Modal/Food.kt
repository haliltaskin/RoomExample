package com.halil.roomcompose.Modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Food")
data class Food(
    var name:String,
    var protein:Double,
    var yag:Double,

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
)
