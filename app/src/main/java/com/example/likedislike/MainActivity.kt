package com.example.likedislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //Module-level variables
    //var countlike:Int = 0
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","Oncreate")

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java
        )
        //initial the Shared Preferences
        //sharedPreferences = getSharedPreferences("natokong",Context.)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDisLike.toString()

        imageViewlike.setOnClickListener{
            counterViewModel.countLike++
            textViewLike.text = counterViewModel.countLike.toString()
        }
        imageViewDislike.setOnClickListener{
            counterViewModel.countDisLike++
            textViewDislike.text = counterViewModel.countDisLike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity","onStart")
        super.onStart()
    }
    override fun onResume() {
        Log.d("MainActivity","onResume")
        //Retrieve Shared Preference value(s)
        counterViewModel.countLike = sharedPreferences.getInt(getString(R.string.like),0)
        counterViewModel.countDisLike = sharedPreferences.getInt(getString(R.string.like),0)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDisLike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity","onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like),counterViewModel.countLike)
            putInt(getString(R.string.like),counterViewModel.countDisLike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity","onStop")
        super.onStop()
    }
    override fun onDestroy() {
        Log.d("MainActivity","onDestroy")
        super.onDestroy()
    }
}
