package com.example.bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var foods: MutableList<DisplayFood> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Lookup the RecyclerView in activity layout
        val foodRv = findViewById<RecyclerView>(R.id.foodRv)
        val adapter = FoodAdapter(foods)
        foodRv.adapter = adapter
        // Set layout manager to position the items
        foodRv.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories,
                      
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        findViewById<Button>(R.id.btnNewFood).setOnClickListener{
            var foodName = findViewById<EditText>(R.id.editName2).text.toString()
            var foodCal = findViewById<EditText>(R.id.editCal2).text.toString()
            var food = DisplayFood(foodName, foodCal)
            foods.add(food)
            findViewById<EditText>(R.id.editName2).text.clear()
            findViewById<EditText>(R.id.editCal2).text.clear()
            adapter.notifyDataSetChanged()
            lifecycleScope.launch(Dispatchers.IO){
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(
                        name = food.name,
                        calories = food.calories

                    )

                )


            }
//

        }

    }
}