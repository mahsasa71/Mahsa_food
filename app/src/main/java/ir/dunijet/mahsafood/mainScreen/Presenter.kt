package ir.dunijet.mahsafood.mainScreen

import ir.dunijet.mahsafood.model.Food
import ir.dunijet.mahsafood.model.FoodDao

class Presenter( private val foodDao: FoodDao):MainContract.Presenter {
    var mainView: MainContract.View? = null
    override fun onAttach(view: MainContract.View) {
        mainView = view

        val data = foodDao.getAllFood()
        mainView!!.showFoods(data)

    }

    override fun onDetach() {
        mainView=null

    }

    override fun firstRun() {
        val FirstRunfoodlist = arrayListOf(


            Food(
                Ratingbar = 1f,
                numbOfRating = 90,
                txtSub = "Hamburger",
                txtDistance = "29",
                txtGheymat = "7",
                txtDiiscription =  "mashhad",
                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
            ),
            Food(
                Ratingbar = 3.5f,
                numbOfRating =22,
                txtSub ="Grilled fish",
                txtDistance ="12",
                txtGheymat ="13",
                txtDiiscription = "zahedan",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg"
            ),
            Food(
                Ratingbar =2.1f,
                numbOfRating = 30,
                txtSub ="Lasania",
                txtDistance = "18",
                txtGheymat ="34",
                txtDiiscription = "arak",
                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            Food(
                Ratingbar =1.5f,
                numbOfRating = 14,
                txtSub = "pitza",
                txtDistance = "321",
                txtGheymat =  "34",
                txtDiiscription ="ardabil",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg"
            ),
            Food(
                Ratingbar =2.5f,
                numbOfRating = 14,
                txtSub ="Sushi",
                txtDistance ="267",
                txtGheymat = "15",
                txtDiiscription = "ghom",
                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg"
            ),
            Food(
                Ratingbar =2.9f,
                numbOfRating =  7,
                txtSub ="Roasted Fish",
                txtDistance ="123",
                txtGheymat ="178",
                txtDiiscription ="turker",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg"
            ),
            Food(
                Ratingbar = 1f,
                numbOfRating =57,
                txtSub ="Fried chicken",
                txtDistance = "127",
                txtGheymat = "145",
                txtDiiscription ="germani",
                UrlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg"
            ),
            Food(
                Ratingbar =   2f,
                numbOfRating =78,
                txtSub = "Vegetable salad",
                txtDistance = "13",
                txtGheymat ="138",
                txtDiiscription ="france",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg"
            ),
            Food(
                Ratingbar =   5f,
                numbOfRating =  83,
                txtSub ="Grilled chicken",
                txtDistance =  "12",
                txtGheymat ="129",
                txtDiiscription = "albani",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg"
            ),
            Food(
                Ratingbar = 3f,
                numbOfRating =32,
                txtSub ="Baryooni",
                txtDistance = "67",
                txtGheymat = "125",
                txtDiiscription =  "tabriz",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg"
            ),
            Food(
                Ratingbar = 1.5f,
                numbOfRating = 46,
                txtSub ="Ghorme Sabzi",
                txtDistance = "93",
                txtGheymat = "174",
                txtDiiscription = "china",
                UrlImage ="https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg"
            ),
            Food(
                Ratingbar =  2.5f,
                numbOfRating =  13,
                txtSub =  "Rice",
                txtDistance ="12",
                txtGheymat = "123",
                txtDiiscription = "iran",
                UrlImage =  "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg"
            )


        )
        foodDao.insertAllfood(FirstRunfoodlist)

    }

    override fun onSearchFood(filter: String) {

        if (filter.isNotEmpty()) {

            // show filtered data
            val dataToShow = foodDao.searchFood(filter)
            mainView!!.refreshFoods(dataToShow)

        } else {

            // show all data
            val dataToShow = foodDao.getAllFood()
            mainView!!.refreshFoods(dataToShow)

        }
    }

    override fun onAddNewFoodClicked(food: Food) {
        foodDao.insertandUpdate(food)
        mainView!!.addNewFood(food)
    }

    override fun onDeleteAllClicked() {

        foodDao.deletAllFood()
        mainView!!.refreshFoods(foodDao.getAllFood())

    }

    override fun onUpdateFood(food: Food, pos: Int) {

        foodDao.insertandUpdate(food)
        mainView!!.updateFood(food, pos)

    }

    override fun onDeleteFood(food: Food, pos: Int) {
        foodDao.deletfood(food)
        mainView!!.deleteFood(food, pos)


    }
}