package com.suitmedia.competencytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.suitmedia.competencytest.databinding.ActivityMainBinding
import com.suitmedia.competencytest.utils.NAME

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener{
            val inputCheckText = binding.edHomePalindrome.text.toString()
            when (inputCheckText.length) {
                0 -> Toast.makeText(this, getString(R.string.error_not_fill), Toast.LENGTH_SHORT).show()
                else -> checkPalindrome(inputCheckText)
            }
        }

        binding.btnNext.setOnClickListener {
            val inputNameText = binding.edHomeName.text.toString()
            when (inputNameText.length) {
                0 -> Toast.makeText(this, getString(R.string.error_not_fill), Toast.LENGTH_SHORT).show()
                else -> goSecondActivity(inputNameText)
            }
        }
    }

    fun checkPalindrome(checkText: String) {
        val checkTextNoSpaces = checkText.replace(" ","")
        val checkTextReversed = checkTextNoSpaces.reversed()
        when {
            checkTextNoSpaces==checkTextReversed -> Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
        }
    }

    fun goSecondActivity(nameText: String) {
        val secondIntent = Intent(this, SecondActivity::class.java)
        secondIntent.putExtra(NAME, nameText)
        startActivity(secondIntent)
    }
}