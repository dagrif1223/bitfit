package com.example.bitfit.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.bitfit.DisplayFood
import com.example.bitfit.Food
import com.example.bitfit.FoodApplication
import com.example.bitfit.R
import kotlinx.coroutines.launch


class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var avgNum = 0
        var maxNum = 0
        var sum = 0
        var minNum = 100000
        var foods: MutableList<DisplayFood> = ArrayList()
        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories,
                    )
                }.also { mappedList ->
                    for(food in mappedList){
                        if(food.calories!!.toInt()>maxNum){
                            maxNum = food.calories!!.toInt()
                        }
                        sum+= food.calories!!.toInt()
                        if(food.calories!!.toInt()<minNum){
                            minNum = food.calories!!.toInt()
                        }
                    }
                    avgNum = sum/mappedList.size
                    view.findViewById<TextView>(R.id.tvAvgNum).text = avgNum.toString()
                    view.findViewById<TextView>(R.id.tvMaxNum).text = maxNum.toString()
                    view.findViewById<TextView>(R.id.tvMinNum).text = minNum.toString()
                }
            }
        }


    }




}