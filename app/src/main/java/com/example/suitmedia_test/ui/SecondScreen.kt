package com.example.suitmedia_test.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmedia_test.R
import com.example.suitmedia_test.databinding.ActivityMainBinding
import com.example.suitmedia_test.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {
    private  lateinit var binding: ActivitySecondScreenBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)

        // Set initial text views
        binding.tvName.text = preferences.getString(MainActivity.NAME, "").toString()
        binding.tvUsername.text = preferences.getString(MainActivity.USERNAME, "").toString()

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == MainActivity.NAME || key == MainActivity.USERNAME) {
                binding.tvName.text = sharedPreferences.getString(MainActivity.NAME, "").toString()
                binding.tvUsername.text = sharedPreferences.getString(MainActivity.USERNAME, "").toString()
            }
        }

        preferences.registerOnSharedPreferenceChangeListener(listener)

        //action button next
        binding.btnNext.setOnClickListener{
            val toThirdScreen = Intent(this@SecondScreen, ThirdScreen::class.java)
            startActivity(toThirdScreen)
        }
    }

}