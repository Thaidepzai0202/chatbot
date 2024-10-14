package com.example.chatbot

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.databinding.LoginScreenBinding

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: LoginScreenBinding
    private var isPasswordVisible = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginScreenBinding.inflate(layoutInflater)
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

        binding.signUpButtonNextScreen.setOnClickListener {
            var intent = Intent(this, RegisterAccountScreen::class.java)
            startActivity(intent)
            finish()
        }

        binding.forgetPasswordNextScreen.setOnClickListener {
            var intent = Intent(this,ForgetPasswordScreen::class.java)
            startActivity(intent)
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                val view = currentFocus
                if (view is EditText) {
                    val outRect = Rect()
                    view.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        view.clearFocus()
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }


}