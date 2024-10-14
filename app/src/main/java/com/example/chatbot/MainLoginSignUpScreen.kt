package com.example.chatbot

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.databinding.MainLoginSignUpScreenBinding

class MainLoginSignUpScreen : AppCompatActivity() {

    private lateinit var binding: MainLoginSignUpScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainLoginSignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isDarkk) {
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.statusBarColor =
                getColor(R.color.background)
        }

        binding.loginButtonNextScreen.setOnClickListener {
            var intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
        binding.registerButtonNextScreen.setOnClickListener {
            var intent = Intent(this, RegisterAccountScreen::class.java)
            startActivity(intent)
        }
    }
}