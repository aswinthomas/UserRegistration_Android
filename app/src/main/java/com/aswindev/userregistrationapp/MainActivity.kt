package com.aswindev.userregistrationapp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.aswindev.userregistrationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        setupButton()
    }

    private fun setupButton() {
        binding.buttonRegister.setOnClickListener {
            val data = UserRegistrationData(
                binding.spinnerTitle.selectedItem?.toString(),
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPhoneNumber.text.toString(),
                binding.editTextPassword.text.toString()
            )
            val summaryActivityIntent = Intent(this, SummaryActivity::class.java)
            summaryActivityIntent.putExtra("data", data)
            startActivity(summaryActivityIntent)
        }
    }

    private fun setupSpinner() {
        var titles: Array<String> = arrayOf(
            "Choose Title",
            "Mr.",
            "Ms.",
            "Mrs.",
            "Miss",
            "Dr.",
            "Prof.",
            "Sir",
            "Madam",
            "Mx.",
            "Rev.",
            "Capt.",
            "Lt.",
            "Sgt."
        )
        val titlesAdapter =
            ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, titles)
        binding.spinnerTitle.adapter = titlesAdapter
    }
}