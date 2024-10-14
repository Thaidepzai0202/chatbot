package com.example.chatbot.adapter

import android.service.autofill.FillRequest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.chatbot.R
import com.bumptech.glide.request.RequestOptions

class FirstPageViewAdapter(
    private val sliderItems: List<SliderItem>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<FirstPageViewAdapter.FirstPageViewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstPageViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.image_viewpage_only_image,
            parent,
            false
        )
        return FirstPageViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    override fun onBindViewHolder(holder: FirstPageViewViewHolder, position: Int) {
        holder.bind(sliderItems[position])
    }

    inner class FirstPageViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView:ImageView = itemView.findViewById(R.id.image_item_viewpage)

        fun bind (sliderItem: SliderItem){
//            imageView.setImageResource(sliderItem.image)
            Glide.with(imageView.context)
                .load(sliderItem.image)
                .apply(
                    RequestOptions().transform(CenterCrop())
                )
                .into(imageView)
        }

    }
}


class SliderItem internal constructor(val image: Int)