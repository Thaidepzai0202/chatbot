package com.example.chatbot

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.bottomsheet.selectemailbottonsheet.SelectEmailBottomSheet
import com.example.chatbot.databinding.ForgetPasswordScreenBinding
import com.example.chatbot.model.AccountModel

class ForgetPasswordScreen : AppCompatActivity() {

    private lateinit var binding: ForgetPasswordScreenBinding

    private var selectedAcc: AccountModel? = null
        set(value) {
            field = value
            binding.selectedAcc = value
        }

    private val accountList = listOf(
        AccountModel("0123456789", "John One ", "john1@example.com"),
        AccountModel("0123456788", "John Two", "john2@example.com"),
        AccountModel("0123456787", "John Three", "john3@example.com"),
        AccountModel("0123456786", "John Four", "john4@example.com"),
        AccountModel("0123456785", "John Five", "john5@example.com"),
        AccountModel("0987654321", "Jane Six", "jane6@example.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ForgetPasswordScreenBinding.inflate(layoutInflater)
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

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.linearLayout7.setOnClickListener {
            showBottomSheet(binding.linearLayout7)
        }

        binding.linearLayout8.setOnClickListener {
            showBottomSheet(binding.linearLayout8)
        }

        binding.nextToVerifyScreen.setOnClickListener {
            var intent = Intent(this, VerifyScreen::class.java)
            intent.putExtra("SELECTED_ACCOUNT",selectedAcc)
            startActivity(intent)
        }

    }

    private fun showBottomSheet(linearLayout: LinearLayout){
        val bottomSheetFragment = SelectEmailBottomSheet(accountList) { selectedAccount ->
            selectedAcc = selectedAccount
        }
        bottomSheetFragment.show(supportFragmentManager,"AccountFragmentBottomSheet")
        binding.linearLayout7.isSelected = false
        binding.linearLayout8.isSelected = false
        linearLayout.isSelected = true;
    }
}