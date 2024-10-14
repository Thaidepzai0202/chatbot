package com.example.chatbot

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.chatbot.databinding.SplashScreenBinding
import kotlinx.coroutines.delay


var isDarkk = false

class Splash1Screen : AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = SplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor =
                getColor(R.color.background)
        }

        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        isDarkk = sharedPref.getBoolean("isDarkMode", false)

        if (isDarkk) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = getColor(R.color.background)
        }

        binding.brainBox.animate().translationY(-500F)
            .setDuration(2000)
            .setStartDelay(2000)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withLayer()

        binding.logoSplash.animate()
            .alpha(0F)
            .setDuration(2000)
            .setStartDelay(2000)
            .start()




        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 6000)
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this, TestLoadingCustom::class.java))
//            finish()
//            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
//        }, 6000)

    }
}