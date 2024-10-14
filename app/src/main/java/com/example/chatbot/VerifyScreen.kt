package com.example.chatbot

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chatbot.databinding.VerifyScreenBinding
import com.example.chatbot.model.AccountModel

class VerifyScreen : AppCompatActivity() {
    private lateinit var binding: VerifyScreenBinding

    private var selectedAcc: AccountModel? = null
        set(value) {
            field = value
            binding.selectedAcc = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VerifyScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedAcc = intent.getParcelableExtra("SELECTED_ACCOUNT")


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

        setupPinDigitFocus()
    }

    private fun setupPinDigitFocus() {
        // Tạo `TextWatcher` để chuyển focus khi nhập đủ 1 ký tự
        binding.pinDigit1.addTextChangedListener(createTextWatcher(binding.pinDigit2))
        binding.pinDigit2.addTextChangedListener(createTextWatcher(binding.pinDigit3))
        binding.pinDigit3.addTextChangedListener(createTextWatcher(binding.pinDigit4))

        // back digit
        binding.pinDigit2.addTextChangedListener(createDeleteKeyListener(binding.pinDigit1))
        binding.pinDigit3.addTextChangedListener(createDeleteKeyListener(binding.pinDigit2))
        binding.pinDigit4.addTextChangedListener(createDeleteKeyListener(binding.pinDigit3))

        //background
        binding.pinDigit1.addTextChangedListener(setBackground(binding.pinDigit1))
        binding.pinDigit2.addTextChangedListener(setBackground(binding.pinDigit2))
        binding.pinDigit3.addTextChangedListener(setBackground(binding.pinDigit3))
        binding.pinDigit4.addTextChangedListener(setBackground(binding.pinDigit4))

    }

    //back digit
    private fun createDeleteKeyListener(prevEditText: View): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {

                    prevEditText.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    //set background
    private fun setBackground(currentEditText: View): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                currentEditText.isSelected = !s.isNullOrEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    //nextDigit
    private fun createTextWatcher(nextEditText: View): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {

                    nextEditText.requestFocus() // Chuyển focus sang ô tiếp theo
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }
}