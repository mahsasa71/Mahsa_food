package ir.dunijet.mahsafood.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {

    @Query("SELECT * FROM table_food")
    fun getAllFood():List<Food>

    @Query("DELETE FROM table_food ")
    fun deletAllFood()

    @Query("SELECT * FROM TABLE_FOOD WHERE txtSub LIKE '%' ||:searching || '%'")
    fun searchFood(searching:String):List<Food>

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deletfood(food: Food)

    @Update
    fun updateFood(food: Food)
}