package ir.dunijet.mahsafood.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(version = 1, exportSchema = false, entities = [Food::class])

abstract class MyDataBase:RoomDatabase (){
    abstract val foodDao:FoodDao

    companion object{
        private var database:MyDataBase ?=null
        fun getmydatabase(context: Context):MyDataBase{
            if (database==null){
                database=Room.databaseBuilder(context.applicationContext,MyDataBase::class.java,"myDataBase.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return database!!
        }
    }
}