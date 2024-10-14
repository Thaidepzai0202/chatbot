package com.example.chatbot

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2
import com.example.chatbot.adapter.FirstPageViewAdapter
import com.example.chatbot.adapter.SliderItem
import com.example.chatbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isUserInteracting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        isDarkk = sharedPref.getBoolean("isDarkMode", false)
        binding.switchTheme.isChecked = isDarkk

        // Đặt màu và trạng thái ban đầu của thanh trạng thái
        updateStatusBar(isDarkk)

        // Cài đặt ViewPager và các trang hiển thị
        val sliderItems = listOf(
            SliderItem(R.drawable.unlock_1),
            SliderItem(R.drawable.chat_with_2),
            SliderItem(R.drawable.boost)
        )
        binding.viewpageFirstApp.adapter =
            FirstPageViewAdapter(sliderItems, binding.viewpageFirstApp)
        binding.circleIndicator3.setViewPager(binding.viewpageFirstApp)

        binding.viewpageFirstApp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.titleAfterSplash.text = "Unlock the Power\n" + "Of  Future AI"
                        binding.contentAfterSplash.text =
                            "Chat with the smartest AI Future\n" + "Experience power of AI with us"
                    }
                    1 -> {
                        binding.titleAfterSplash.text = "Chat With Your\n" + "Favourite Ai"
                        binding.contentAfterSplash.text =
                            "Chat with the smartest AI Future\n" + "Experience power of AI with us"
                    }
                    2 -> {
                        binding.titleAfterSplash.text = "Boost Your Mind\n" + "Power With Ai"
                        binding.contentAfterSplash.text =
                            "Chat with the smartest AI Future\n" + "Experience power of AI with us"
                    }
                }
            }
        })

        // Điều khiển nút chuyển trang
        binding.prevPageInFirstScreen.setOnClickListener {
            binding.viewpageFirstApp.currentItem--
        }
        binding.nextPageInFirstScreen.setOnClickListener {
            if(binding.viewpageFirstApp.currentItem==2){
                nextScreen()
            }else{
                binding.viewpageFirstApp.currentItem++
            }

        }

        // Sự kiện thay đổi trạng thái switch theme
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isUserInteracting) { // Chỉ thay đổi khi người dùng thực sự tương tác
                if (isChecked != isDarkk) { // Kiểm tra xem trạng thái có thực sự thay đổi không
                    isDarkk = isChecked
                    with(sharedPref.edit()) {
                        putBoolean("isDarkMode", isChecked)
                        apply()
                    }
                    // Cập nhật giao diện theo chế độ sáng/tối
                    updateStatusBar(isChecked)
                    AppCompatDelegate.setDefaultNightMode(
                        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
            }
        }

        binding.skipButton.setOnClickListener {
            nextScreen()
        }
    }

    private fun updateStatusBar(isDarkMode: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                if (isDarkMode)
                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                else
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = getColor(R.color.background)
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        isUserInteracting = true
    }

    private  fun nextScreen(){
        val intent = Intent(this, MainLoginSignUpScreen::class.java)
        startActivity(intent)
    }
}
