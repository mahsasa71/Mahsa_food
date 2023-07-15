package ir.dunijet.mahsafood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.dunijet.mahsafood.databinding.ActivityMain2Binding
import ir.dunijet.mahsafood.databinding.ActivityMainBinding
import ir.dunijet.mahsafood.databinding.DialodremoveBinding
import ir.dunijet.mahsafood.databinding.DialogAddFoodBinding
import ir.dunijet.mahsafood.databinding.DialogUpdateFoodBinding
import ir.dunijet.mahsafood.room.Food


class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvent {

    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: FoodAdapter
    lateinit var foodList: ArrayList<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        val foodlist = arrayListOf(
//
//
//            Food(
//                1f,
//                90,
//                "Hamburger",
//                "29",
//                "7",
//                "mashhad",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
//            ),
//            Food(
//                3.5f,
//                22,
//                "Grilled fish",
//                "12",
//                "13",
//                "zahedan",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg"
//            ),
//            Food(
//                2.1f,
//                30,
//                "Lasania",
//                "18",
//                "34",
//                "arak",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
//            ),
//            Food(
//                1.5f,
//                14,
//                "pitza",
//                "321",
//                "34",
//                "ardabil",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg"
//            ),
//            Food(
//                2.5f,
//                14,
//                "Sushi",
//                "267",
//                "15",
//                "ghom",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg"
//            ),
//            Food(
//                2.9f,
//                7,
//                "Roasted Fish",
//                "123",
//                "178",
//                "turker",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg"
//            ),
//            Food(
//                1f,
//                57,
//                "Fried chicken",
//                "127",
//                "145",
//                "germani",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg"
//            ),
//            Food(
//                2f,
//                78,
//                "Vegetable salad",
//                "13",
//                "138",
//                "france",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg"
//            ),
//            Food(
//                5f,
//                83,
//                "Grilled chicken",
//                "12",
//                "129",
//                "albani",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg"
//            ),
//            Food(
//                3f,
//                32,
//                "Baryooni",
//                "67",
//                "125",
//                "tabriz",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg"
//            ),
//            Food(
//                1.5f,
//                46,
//                "Ghorme Sabzi",
//                "93",
//                "174",
//                "china",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg"
//            ),
//            Food(
//                2.5f,
//                13,
//                "Rice",
//                "12",
//                "123",
//                "iran",
//                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg"
//            ),
//
//
//            )
//
//        myadapter = FoodAdapter(foodlist.clone() as ArrayList<Food>, this, this)
//        binding.recyclermain.adapter = myadapter
//        binding.recyclermain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//
//
//
//        binding.dokmradd.setOnClickListener {
////
////            val random:Int=(1..256).random()
////            val rang=Color.rgb(23,random,random)
////            binding.recyclermain.setBackgroundColor(random)
//
//            val dialog = AlertDialog.Builder(this).create()
//            val dialogbidingadd = DialogAddFoodBinding.inflate(layoutInflater)
//            dialog.setView(dialogbidingadd.root)
//            dialog.setCancelable(true)
//            dialog.show()
//            dialogbidingadd.dialogBtnDone1.setOnClickListener {
//                if (dialogbidingadd.edtCity.length() > 0 && dialogbidingadd.edtPric.length() > 0 && dialogbidingadd.edtdistance.length() > 0
//                    && dialogbidingadd.edtfood.length() > 0
//                ) {
//
//                    val txtName = dialogbidingadd.edtfood.text.toString()
//                    val txtprice = dialogbidingadd.edtPric.text.toString()
//                    val edtdistance = dialogbidingadd.edtdistance.text.toString()
//                    val edtcity = dialogbidingadd.edtCity.text.toString()
//                    val numofrating: Int = (1..150).random()
//                    val ratingstar: Float = (1..5).random().toFloat()
//                    val randomurl = (1..11).random()
//                    val urlpic = foodlist[randomurl].UrlImage
//                    val newfood = Food(
//                        ratingstar,
//                        numofrating,
//                        txtName,
//                        edtdistance,
//                        txtprice,
//                        edtcity,
//                        urlpic
//                    )
//                    myadapter.addFood(newfood)
//                    binding.recyclermain.scrollToPosition(0)
//                    dialog.dismiss()
//                } else {
//                    Toast.makeText(this, "لطفا همه ی مقادیر را وارد کنید", Toast.LENGTH_LONG).show()
//                }
//
//
//            }
//
//
//        }
//
//        binding.edtSearch.addTextChangedListener {meghdaredittext->
//            if (meghdaredittext!!.length > 0) {
//                val clonelist=foodlist.clone() as ArrayList<Food>
//                val filterlist1=clonelist.filter {fooditem->
//                    fooditem.txtSub.contains(meghdaredittext)
//
//
//                }
//                myadapter.setData(ArrayList(filterlist1))
//
//            } else {
//                val clonelist=foodlist.clone() as ArrayList<Food>
//                myadapter.setData(clonelist)
//
//            }
//        }


    }

    override fun onItemclicked(food: Food, position: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//        val dialogbindingupdate = DialogUpdateFoodBinding.inflate(layoutInflater)
//        dialog.setView(dialogbindingupdate.root)
//        dialog.setCancelable(true)
//        dialog.show()
//
//        dialogbindingupdate.edtfoodUpdate.setText(food.txtSub)
//        dialogbindingupdate.edtCityUpdate.setText(food.txtDiiscription)
//        dialogbindingupdate.edtdistanceUpdate.setText(food.txtDistance)
//        dialogbindingupdate.edtPricUpdate.setText(food.txtGheymat)
//        dialogbindingupdate.btnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogbindingupdate.dialogBtnDone.setOnClickListener {
//            if (dialogbindingupdate.edtCityUpdate.length() > 0 && dialogbindingupdate.edtPricUpdate.length() > 0 && dialogbindingupdate.edtPricUpdate.length() > 0
//                && dialogbindingupdate.edtfoodUpdate.length() > 0
//            ) {
//                val txtsub2 = dialogbindingupdate.edtfoodUpdate.text.toString()
//                val txtdistance = dialogbindingupdate.edtdistanceUpdate.text.toString()
//                val txtprice = dialogbindingupdate.edtPricUpdate.text.toString()
//                val txtcity = dialogbindingupdate.edtCityUpdate.text.toString()
//                val newfood = Food(
//                    food.Ratingbar,
//                    food.numbOfRating,
//                    txtsub2,
//                    txtdistance,
//                    txtprice,
//                    txtcity,
//                    food.UrlImage
//                )
//                myadapter.updateFood(newfood, position)
//                dialog.dismiss()
//            } else {
//                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید", Toast.LENGTH_SHORT).show()
//            }
//        }


    }


    override fun onItemLongclicked(food: Food, position: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//        val dialogbindigremove = DialodremoveBinding.inflate(layoutInflater)
//        dialog.setView(dialogbindigremove.root)
//        dialog.setCancelable(true)
//        dialog.show()
//        dialogbindigremove.btnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogbindigremove.btnDone.setOnClickListener {
//
//            myadapter.removeFood(food, position)
//            dialog.dismiss()
//        }


    }


}