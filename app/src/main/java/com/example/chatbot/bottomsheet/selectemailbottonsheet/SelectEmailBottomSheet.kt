package com.example.chatbot.bottomsheet.selectemailbottonsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbot.bottomsheet.selectemailbottonsheet.adapter.SelectAccountAdapter
import com.example.chatbot.databinding.FragmentSelectAccountBottomSheetBinding
import com.example.chatbot.model.AccountModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectEmailBottomSheet(
    private val accountList: List<AccountModel>,
    private val onAccountSelected: (AccountModel) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSelectAccountBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAccountBottomSheetBinding.inflate(layoutInflater)
        val view = binding.root

        binding.listAccount.layoutManager = LinearLayoutManager(context)
        binding.listAccount.adapter = SelectAccountAdapter(accountList){
            account -> onAccountSelected(account)
            dismiss()
        }

        return view
    }

}