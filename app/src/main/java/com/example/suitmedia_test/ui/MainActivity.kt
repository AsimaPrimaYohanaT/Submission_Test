package com.example.suitmedia_test.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.example.suitmedia_test.R
import com.example.suitmedia_test.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    //preference for save data name and user
    companion object {
        const val SHARED_PREFERENCES = "shared_preferences"
        const val NAME = "name"
        const val USERNAME = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

        //hide action bar
        supportActionBar?.hide()

        //button check action
        binding.btnCheck.setOnClickListener {
            val name = binding.editName.text.toString()
            val palindrome = binding.editPalindrome.text.toString()

            if((palindrome == "palindrome"|| palindrome == "Palindrome") && name.isNotEmpty()){
                val message = if(palindromeCheck(name)) "Palindrome" else "Not Palindrome"
                displayAlertDialog(message)
            }
        }

        //action button next
        binding.btnNext.setOnClickListener {
            val name = binding.editName.text.toString()
            save(name)
            val toSecondScreen = Intent(this@MainActivity, SecondScreen::class.java)
            startActivity(toSecondScreen)
        }

    }

    //function for save name in preference
    private fun save(name: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(NAME, name)
        editor.apply()
    }


    //function for palindrome check
    private fun palindromeCheck(name: String):Boolean{

        //replace all string to lower case
        val toLowerString = name.lowercase(Locale.ROOT).replace(Regex("[^a-zA-Z0-9]"),"")
        val lengthString = toLowerString.length

        for(i in 0 until lengthString/2){
            if(toLowerString[i] != toLowerString[lengthString - i-1]){
                return false
            }
        }
        return true
    }

    // Function to display AlertDialog
    private fun displayAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Result")
            .setMessage(message)
            .setPositiveButton(getString(R.string.return_alert)) { dialog, _ ->
                dialog.dismiss()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}