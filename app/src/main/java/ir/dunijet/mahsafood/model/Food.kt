package ir.dunijet.mahsafood.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_food")
data class Food(

@PrimaryKey(autoGenerate = true)
val id : Int?=null,

    val Ratingbar:Float,
    val numbOfRating:Int,
    val txtSub:String,
    val txtDistance:String,
    val txtGheymat:String,
    val txtDiiscription:String,
    val UrlImage:String,

)
