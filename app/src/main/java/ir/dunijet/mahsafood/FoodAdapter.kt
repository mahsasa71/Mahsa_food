package ir.dunijet.mahsafood

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ir.dunijet.mahsafood.room.Food
import kotlin.random.Random

class FoodAdapter(private val data:ArrayList<Food>, private val context:Context, private val foodEvent: FoodEvent):RecyclerView.Adapter<FoodAdapter.FoodVH> () {


    inner class FoodVH(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val Imgghaza = itemview.findViewById<ImageView>(R.id.ImgGhaza)
        val txtFasele = itemview.findViewById<TextView>(R.id.txt_Faseleh)
        val txtgheimat = itemview.findViewById<TextView>(R.id.txt_gheymat)
        val numberRating = itemview.findViewById<TextView>(R.id.textViewRating)
        val txtDescription = itemview.findViewById<TextView>(R.id.txtDescription)
        val txtTitle = itemview.findViewById<TextView>(R.id.txttitle)
        val Rating = itemview.findViewById<RatingBar>(R.id.ratingBar)


        fun onbind(position: Int) {
            txtFasele.text = data[position].txtDistance + "miles from you"
            txtgheimat.text = "$" + data[position].txtGheymat + "vip"
            txtDescription.text = data[position].txtDiiscription
            txtTitle.text = data[position].txtSub
            Rating.rating = data[position].Ratingbar
            numberRating.text = data[position].numbOfRating.toString() + "Ratings"
            Glide.with(context)
                .load(data[position].UrlImage)
                .transform(RoundedCorners(16))
                .into(Imgghaza)

            itemView.setOnClickListener {
                foodEvent.onItemclicked(data[adapterPosition],adapterPosition)

            }
            itemView.setOnLongClickListener {
                foodEvent.onItemLongclicked(data[adapterPosition],adapterPosition)

                true
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodVH(view)


    }


    //@SuppressLint("NewApi")
    override fun onBindViewHolder(holder: FoodVH, position: Int) {
        holder.onbind(position)

        //    holder.itemView.setBackgroundColor(holder.itemView.resources.getColor((random()),null))


        val adadrandom: Int = (1..256).random()
        val rang = Color.rgb(adadrandom, random(), random())
        holder.itemView.setBackgroundColor(rang)


    }


    override fun getItemCount(): Int {
        return data.size

    }

    private fun random(): Int {

        val list = listOf(
            R.drawable.shapeghash,
            R.color.orange,
            R.color.zard,
            R.color.gray_light,
            android.R.color.holo_red_light,
            android.R.color.holo_green_light,
            android.R.color.holo_purple,
            R.color.zard,
            R.color.soorati
        )
        val randomlist = list.random()
        return randomlist


    }

    fun addFood(newFood: Food) {
        data.add(0, newFood)
        notifyItemInserted(0)
    }

    fun removeFood(oldfood: Food, oldposition: Int) {
        data.remove(oldfood)
        notifyItemRemoved(oldposition)
    }
   fun updateFood(food: Food, position: Int){
       data.set(position,food)
       notifyItemChanged(position)
   }
    fun setData(Newist: ArrayList<Food>){
        data.clear()
        data.addAll(Newist)
        notifyDataSetChanged()

    }

        interface FoodEvent {
            fun onItemclicked(food: Food, position: Int)
            fun onItemLongclicked(food: Food, position: Int)

        }


}