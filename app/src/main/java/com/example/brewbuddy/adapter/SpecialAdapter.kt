package com.example.brewbuddy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.activity.DetailActivity
import com.example.brewbuddy.databinding.ViewholderSpecialBinding
import com.example.brewbuddy.domain.ItemModel

class SpecialAdapter(val items: MutableList<ItemModel>) :
    RecyclerView.Adapter<SpecialAdapter.ViewHolder>() {
        lateinit var context : Context
    class ViewHolder(val binding: ViewholderSpecialBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderSpecialBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxtSpecial.text=items[position].title
        holder.binding.priceTxtSpecial.text="₹"+items[position].price.toString()
        holder.binding.ratingBar.rating=items[position].rating.toFloat()
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.picMain)

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("object", items[position])
                }
            )
        }
    }

    override fun getItemCount(): Int = items.size

}