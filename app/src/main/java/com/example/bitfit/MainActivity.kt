package com.example.bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.fragments.DashboardFragment
import com.example.bitfit.fragments.LogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //var foods: MutableList<DisplayFood> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager : FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{
            item ->
            var fragmentToShow : Fragment? = null
            when(item.itemId){
                R.id.action_log -> {
                    fragmentToShow = LogFragment()
                }
                R.id.action_dashboard -> {
                    fragmentToShow = DashboardFragment()
                }
            }
            if(fragmentToShow!=null){
                fragmentManager.beginTransaction().replace(R.id.flContainer,fragmentToShow).commit()
            }
        true
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_log

    }
}