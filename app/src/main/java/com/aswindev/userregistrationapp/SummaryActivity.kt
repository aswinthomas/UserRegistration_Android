package com.aswindev.userregistrationapp

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aswindev.userregistrationapp.databinding.ActivitySummaryBinding
import java.io.Serializable

fun <T : Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    private lateinit var data: UserRegistrationData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = intent.intentSerializable(
            "data",
            UserRegistrationData::class.java
        ) as UserRegistrationData

        val fullNameText = "Name: "+ data.getFullName()
        val emailText = "Email Id: " + data.emailId
        val phoneText = "Phone Number: " + data.phoneNumber
        binding.textViewUserName.text = fullNameText
        binding.textViewEmail.text = emailText
        binding.textViewPhoneNumber.text = phoneText

        // underline the text
        binding.textViewEmail.paintFlags = binding.textViewEmail.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.textViewPhoneNumber.paintFlags = binding.textViewPhoneNumber.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.textViewEmail.setOnClickListener {
            onEmailClicked()
        }

        binding.textViewPhoneNumber.setOnClickListener {
            onPhoneNumberClicked()
        }
    }

    private fun onEmailClicked() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto: $data.emailId") // Only email apps handle this.
        }
        startActivity(intent)
    }

    private fun onPhoneNumberClicked() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$data.phoneNumber")
        }
        startActivity(intent)
    }
}