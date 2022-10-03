package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {
    var foods: MutableList<Food> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        val foodRv = findViewById<RecyclerView>(R.id.foodRv)
//        val adapter = FoodAdapter(foods)
//        foodRv.adapter = adapter
//        // Set layout manager to position the items
//        foodRv.layoutManager = LinearLayoutManager(this)

        val actionBar = supportActionBar
        if(actionBar!= null){
            actionBar.title = "Detail Activity"
        }
        findViewById<Button>(R.id.btnSubmit).setOnClickListener{
            var foodName = findViewById<EditText>(R.id.editName).text.toString()
            var foodCal = findViewById<EditText>(R.id.editCalories).text.toString()
            var food = Food(foodName, foodCal)
            foods.add(food)
            findViewById<EditText>(R.id.editName).text.clear()
            findViewById<EditText>(R.id.editCalories).text.clear()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //adapter.notifyDataSetChanged()
        }
    }
}