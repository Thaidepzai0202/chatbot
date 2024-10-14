package com.example.chatbot

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.databinding.RegisterAccountScreenBinding

class RegisterAccountScreen : AppCompatActivity() {

    private lateinit var binding: RegisterAccountScreenBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterAccountScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(isDarkk){
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else{
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.statusBarColor =
                getColor(R.color.background)
        }

        binding.editTextPassword.setOnTouchListener { _, event ->
            val drawableEnd = 2 // Vị trí drawableEnd (icon cuối) là 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.editTextPassword.right - binding.editTextPassword.compoundDrawables[drawableEnd].bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    showHidePassword(isPasswordVisible)
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.backButton.setOnClickListener { finish() }

        binding.signInButtonNextScreen.setOnClickListener {
            var intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

        binding.googleButtonNextScreen.setOnClickListener {
            var intent = Intent(this, TestLoadingCustom::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun showHidePassword(isVisible: Boolean) {
        if (isVisible) {
            // Hiển thị mật khẩu
            binding.editTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.lock_icon, 0, R.drawable.eye_on_icon, 0
            )
        } else {
            // Ẩn mật khẩu
            binding.editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.lock_icon, 0, R.drawable.eye_off_icon, 0
            )
        }
        // Đặt con trỏ ở cuối văn bản
        binding.editTextPassword.setSelection(binding.editTextPassword.text.length)
    }
}