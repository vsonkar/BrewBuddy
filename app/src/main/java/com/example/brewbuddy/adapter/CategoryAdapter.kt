package com.example.brewbuddy.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.brewbuddy.R
import com.example.brewbuddy.activity.ItemListActivity
import com.example.brewbuddy.databinding.ViewholderCategoryBinding
import com.example.brewbuddy.domain.CategoryModel

class CategoryAdapter(val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    class ViewHolder (val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(context, ItemListActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)

                }
                context.startActivity(intent)
            }, 500)
        }

        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_bg)
        } else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
        }

    }


    override fun getItemCount(): Int {
        return items.size
    }

}