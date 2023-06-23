package com.halil.roomcompose.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.halil.roomcompose.Dao.FoodDao
import com.halil.roomcompose.Modal.Food

@Database(
    entities = [Food::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase :RoomDatabase()
{

    abstract fun dao():FoodDao

    companion object{
        private var INSTANCE : AppDataBase? = null

        fun getDatabase(context:Context):AppDataBase{

            var tmpInstance= INSTANCE
            if (tmpInstance != null){
                return tmpInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}