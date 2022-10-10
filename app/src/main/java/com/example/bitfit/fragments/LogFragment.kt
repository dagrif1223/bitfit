package com.example.bitfit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false)
    }
    var foods: MutableList<DisplayFood> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodRv = view.findViewById<RecyclerView>(R.id.foodRv)
        val adapter = FoodAdapter(foods)
        foodRv.adapter = adapter
        // Set layout manager to position the items
        foodRv.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
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
        view.findViewById<Button>(R.id.btnNewFood).setOnClickListener{
            var foodName = view.findViewById<EditText>(R.id.editName2).text.toString()
            var foodCal = view.findViewById<EditText>(R.id.editCal2).text.toString()
            var food = DisplayFood(foodName, foodCal)
            foods.add(food)
            view.findViewById<EditText>(R.id.editName2).text.clear()
            view.findViewById<EditText>(R.id.editCal2).text.clear()
            adapter.notifyDataSetChanged()
            lifecycleScope.launch(Dispatchers.IO){
                (requireActivity().application as FoodApplication).db.foodDao().insert(
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