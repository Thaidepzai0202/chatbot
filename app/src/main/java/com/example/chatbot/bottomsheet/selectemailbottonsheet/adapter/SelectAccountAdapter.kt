package com.example.chatbot.bottomsheet.selectemailbottonsheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.SelectEmailItemBinding
import com.example.chatbot.model.AccountModel

class SelectAccountAdapter(
    private val accountList: List<AccountModel>,
    private val onAccountSelected : (AccountModel)->Unit
) : RecyclerView.Adapter<SelectAccountAdapter.AccountViewHolder>() {

    inner class AccountViewHolder(private val binding : SelectEmailItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(account : AccountModel){
            binding.nameAccount.text = account.name
            binding.emailAccount.text = account.email
            binding.root.setOnClickListener {
                onAccountSelected(account)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectAccountAdapter.AccountViewHolder {
        val binding = SelectEmailItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectAccountAdapter.AccountViewHolder, position: Int) {
        holder.bind(accountList[position])
    }

    override fun getItemCount(): Int = accountList.size
}