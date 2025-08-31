package com.example.brewbuddy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.brewbuddy.databinding.ViewholderCartBinding
import com.example.brewbuddy.domain.ItemModel
import com.example.brewbuddy.helper.ChangeNumberItemsListener
import com.example.brewbuddy.helper.ManagmentCart

class CartAdapter(private  val listItemSelected: ArrayList<ItemModel>,
    context: Context, var changeNumberItemsListener: ChangeNumberItemsListener?=null)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

        class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)

        private val managmentCart = ManagmentCart(context)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CartAdapter.ViewHolder {
            val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
            val item = listItemSelected[position]
            holder.binding.titleCart.text = item.title
            holder.binding.feeEachItem.text = "₹${item.price}"
            holder.binding.totalItem.text = "₹${Math.round(item.numberInCart * item.price)}"
            holder.binding.numberItemCart.text = item.numberInCart.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .apply(RequestOptions().transform(CenterCrop()))
                .into(holder.binding.picCart)

            holder.binding.plusEachItem.setOnClickListener {
                managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }

            holder.binding.minusEachItem.setOnClickListener {
                managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }

            holder.binding.removeItemBtn.setOnClickListener {
                managmentCart.removeItem(
                    listItemSelected,
                    position,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }
                    })
            }
        }

        override fun getItemCount(): Int = listItemSelected.size

    }