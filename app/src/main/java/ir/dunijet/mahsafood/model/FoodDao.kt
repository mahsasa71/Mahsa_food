package ir.dunijet.mahsafood.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM table_food")
    fun getAllFood():List<Food>

    @Query("DELETE FROM table_food ")
    fun deletAllFood()

    @Query("SELECT * FROM TABLE_FOOD WHERE txtSub LIKE '%' ||:searching || '%'")
    fun searchFood(searching:String):List<Food>

//    @Insert
//    fun insertFood(food: Food)

    @Insert
    fun insertAllfood(data:List<Food>)

    @Delete
    fun deletfood(food: Food)

//    @Update
//    fun updateFood(food: Food)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertandUpdate(food: Food)

}