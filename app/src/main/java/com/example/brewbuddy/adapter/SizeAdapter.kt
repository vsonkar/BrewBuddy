package com.example.brewbuddy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.ViewholderSizeBinding

class SizeAdapter (val items: MutableList<String>) : RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    inner class ViewHolder (val binding: ViewholderSizeBinding):
        RecyclerView.ViewHolder(binding.root)

    private var selectedPosition=-1
    private var lastSelectedPosition = -1
    private lateinit var context: Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SizeAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.ViewHolder, position: Int) {
        val imageSize = when(position) {
            0 -> 45.dpToPx(context)
            1 -> 50.dpToPx(context)
            2 -> 55.dpToPx(context)
            3 -> 60.dpToPx(context)
            else -> 65.dpToPx(context)
        }
            val layoutParams = holder.binding.img.layoutParams
            layoutParams.width=imageSize
            layoutParams.height=imageSize
            holder.binding.img.layoutParams=layoutParams

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position){
            holder.binding.root.setBackgroundResource(R.drawable.orange_bg)
        }else{
            holder.binding.root.setBackgroundResource(R.drawable.stroke_bg)
        }
        }



    override fun getItemCount(): Int {
        return items.size
    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

}