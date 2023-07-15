package ir.dunijet.mahsafood.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.dunijet.mahsafood.databinding.ActivityMainBinding
import ir.dunijet.mahsafood.databinding.DialodremoveBinding
import ir.dunijet.mahsafood.databinding.DialogAddFoodBinding
import ir.dunijet.mahsafood.databinding.DialogUpdateFoodBinding
import ir.dunijet.mahsafood.model.Food
import ir.dunijet.mahsafood.model.FoodDao
import ir.dunijet.mahsafood.model.MyDataBase
import ir.dunijet.mahsafood.util.showToast

const val BASE_URL_IMAGE="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food"

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvent,MainContract.View {

    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: FoodAdapter
    lateinit var foodDao: FoodDao
    lateinit var foodList: ArrayList<Food>
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        presenter = Presenter(MyDataBase.getMyDatabase(this).foodDao)
      //  foodDao= MyDataBase.getMyDatabase(this).foodDao

        val sharedpref=getSharedPreferences("MahsaFood", MODE_PRIVATE)
        if (sharedpref.getBoolean("first_run",true)){

            presenter.firstRun()
            //firstrun()
            sharedpref.edit().putBoolean("first_run",false).apply()

        }
        presenter.onAttach(this)

       // showAlldata()
        binding.btnDeletAll.setOnClickListener {
            //removeAllData()
            presenter.onDeleteAllClicked()
        }




        binding.dokmradd.setOnClickListener {
            addNewFood()


        }

       binding.edtSearch.addTextChangedListener {meghdaredittext->
           presenter.onSearchFood(meghdaredittext.toString())
           //searchonDaatabase(meghdaredittext!!.toString())



    }


    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
//    private fun searchonDaatabase(meghdaredittext: String) {
//        if (meghdaredittext!!.length > 0) {
//
//            val searchdata=foodDao.searchFood(meghdaredittext)
//            myadapter.setData(ArrayList(searchdata))
//
//        } else {
//
//            val data=foodDao.getAllFood()
//            myadapter.setData(ArrayList(data))
//
//        }
//    }
    private fun addNewFood() {

        val dialog = AlertDialog.Builder(this).create()
        val dialogbidingadd = DialogAddFoodBinding.inflate(layoutInflater)
        dialog.setView(dialogbidingadd.root)
        dialog.setCancelable(true)
        dialog.show()
        dialogbidingadd.dialogBtnDone1.setOnClickListener {
            if (dialogbidingadd.edtCity.length() > 0 && dialogbidingadd.edtPric.length() > 0 && dialogbidingadd.edtdistance.length() > 0
                && dialogbidingadd.edtfood.length() > 0
            ) {

                val txtName = dialogbidingadd.edtfood.text.toString()
                val txtprice = dialogbidingadd.edtPric.text.toString()
                val edtdistance = dialogbidingadd.edtdistance.text.toString()
                val edtcity = dialogbidingadd.edtCity.text.toString()
                val numofrating: Int = (1..150).random()
                val ratingstar: Float = (1..5).random().toFloat()
                val randomurl = (1..12).random()
                val urlpic = BASE_URL_IMAGE +randomurl.toString()+".jpg"
                val newfood = Food(
                  Ratingbar =   ratingstar,
                   numbOfRating =  numofrating,
                   txtSub =  txtName,
                    txtDistance = edtdistance,
                    txtGheymat = txtprice,
                  txtDiiscription =   edtcity,
                   UrlImage =  urlpic
                )
                presenter.onAddNewFoodClicked(newfood)
//                myadapter.addFood(newfood)
//                foodDao.insertandUpdate(newfood)
//                binding.recyclermain.scrollToPosition(0)
                dialog.dismiss()
            } else {
              showToast("لطفا همه مقادیر را وارد کنید :)")
            }


        }


    }

//    private fun removeAllData() {
//        foodDao.deletAllFood()
//        showAlldata()
//    }

//    private fun firstrun(){
//        val foodlist = arrayListOf(
//
//
//            Food(
//                Ratingbar = 1f,
//                numbOfRating = 90,
//                txtSub = "Hamburger",
//                txtDistance = "29",
//                txtGheymat = "7",
//               txtDiiscription =  "mashhad",
//                 UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
//            ),
//            Food(
//                Ratingbar = 3.5f,
//                numbOfRating =22,
//                txtSub ="Grilled fish",
//                txtDistance ="12",
//                txtGheymat ="13",
//                txtDiiscription = "zahedan",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg"
//            ),
//            Food(
//                Ratingbar =2.1f,
//                numbOfRating = 30,
//                txtSub ="Lasania",
//                txtDistance = "18",
//                txtGheymat ="34",
//                txtDiiscription = "arak",
//                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
//            ),
//            Food(
//                Ratingbar =1.5f,
//                numbOfRating = 14,
//                txtSub = "pitza",
//                txtDistance = "321",
//                txtGheymat =  "34",
//                txtDiiscription ="ardabil",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg"
//            ),
//            Food(
//                Ratingbar =2.5f,
//                numbOfRating = 14,
//                txtSub ="Sushi",
//                txtDistance ="267",
//                txtGheymat = "15",
//                txtDiiscription = "ghom",
//                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg"
//            ),
//            Food(
//                Ratingbar =2.9f,
//                numbOfRating =  7,
//                txtSub ="Roasted Fish",
//                txtDistance ="123",
//                txtGheymat ="178",
//                txtDiiscription ="turker",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg"
//            ),
//            Food(
//                Ratingbar = 1f,
//                numbOfRating =57,
//                txtSub ="Fried chicken",
//                txtDistance = "127",
//                txtGheymat = "145",
//                txtDiiscription ="germani",
//                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg"
//            ),
//            Food(
//                Ratingbar =   2f,
//                numbOfRating =78,
//                txtSub = "Vegetable salad",
//                txtDistance = "13",
//                txtGheymat ="138",
//                txtDiiscription ="france",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg"
//            ),
//            Food(
//                Ratingbar =   5f,
//                numbOfRating =  83,
//                txtSub ="Grilled chicken",
//                txtDistance =  "12",
//                txtGheymat ="129",
//                txtDiiscription = "albani",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg"
//            ),
//            Food(
//                Ratingbar = 3f,
//                numbOfRating =32,
//                txtSub ="Baryooni",
//                txtDistance = "67",
//                txtGheymat = "125",
//                txtDiiscription =  "tabriz",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg"
//            ),
//            Food(
//                Ratingbar = 1.5f,
//                numbOfRating = 46,
//                txtSub ="Ghorme Sabzi",
//                txtDistance = "93",
//                txtGheymat = "174",
//                txtDiiscription = "china",
//                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg"
//            ),
//            Food(
//                Ratingbar =  2.5f,
//                numbOfRating =  13,
//                txtSub =  "Rice",
//                txtDistance ="12",
//                txtGheymat = "123",
//                txtDiiscription = "iran",
//                UrlImage =  "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg"
//            )
//
//
//            )
//        foodDao.insertAllfood(foodlist)
//
//    }
//    private fun showAlldata(){
//        val foodData=foodDao.getAllFood()
//                myadapter = FoodAdapter(ArrayList(foodData), this, this)
//        binding.recyclermain.adapter = myadapter
//        binding.recyclermain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//
//    }

    override fun onItemclicked(food: Food, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val dialogbindingupdate = DialogUpdateFoodBinding.inflate(layoutInflater)
        dialog.setView(dialogbindingupdate.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogbindingupdate.edtfoodUpdate.setText(food.txtSub)
        dialogbindingupdate.edtCityUpdate.setText(food.txtDiiscription)
        dialogbindingupdate.edtdistanceUpdate.setText(food.txtDistance)
        dialogbindingupdate.edtPricUpdate.setText(food.txtGheymat)
        dialogbindingupdate.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialogbindingupdate.dialogBtnDone.setOnClickListener {
            if (dialogbindingupdate.edtCityUpdate.length() > 0 && dialogbindingupdate.edtPricUpdate.length() > 0 && dialogbindingupdate.edtPricUpdate.length() > 0
                && dialogbindingupdate.edtfoodUpdate.length() > 0
            ) {
                val txtsub2 = dialogbindingupdate.edtfoodUpdate.text.toString()
                val txtdistance = dialogbindingupdate.edtdistanceUpdate.text.toString()
                val txtprice = dialogbindingupdate.edtPricUpdate.text.toString()
                val txtcity = dialogbindingupdate.edtCityUpdate.text.toString()
                val newfood = Food(
                    id=food.id,
                    Ratingbar = food.Ratingbar,
                    numbOfRating = food.numbOfRating,
                    txtSub = txtsub2,
                    txtDistance = txtdistance,
                    txtGheymat =  txtprice,
                    txtDiiscription = txtcity,
                    UrlImage = food.UrlImage
                )
                presenter.onUpdateFood(newfood, position)
//                myadapter.updateFood(newfood, position)
//                foodDao.insertandUpdate(newfood)
                dialog.dismiss()
            } else {
               showToast("لطفا همه مقادیر را وارد کنید")
            }
        }


    }


    override fun onItemLongclicked(food: Food, position: Int) {
       val dialog = AlertDialog.Builder(this).create()
       val dialogbindigremove = DialodremoveBinding.inflate(layoutInflater)
       dialog.setView(dialogbindigremove.root)
       dialog.setCancelable(true)
       dialog.show()
       dialogbindigremove.btnCancel.setOnClickListener {
           dialog.dismiss()
       }
       dialogbindigremove.btnDone.setOnClickListener {
           presenter.onDeleteFood(food,position)

//           myadapter.removeFood(food, position)
//           foodDao.deletfood(food)
           dialog.dismiss()
       }


    }

    override fun showFoods(data: List<Food>) {
        myadapter = FoodAdapter(ArrayList(data), this)
        binding.recyclermain.adapter = myadapter
        binding.recyclermain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    override fun refreshFoods(data: List<Food>) {
        myadapter.setData(ArrayList(data))
    }

    override fun addNewFood(newFood: Food) {
        myadapter.addFood(newFood)

    }

    override fun deleteFood(oldFood: Food, pos: Int) {
        myadapter.removeFood(oldFood, pos)
    }

    override fun updateFood(editingFood: Food, pos: Int) {
        myadapter.updateFood(editingFood, pos)
    }


}