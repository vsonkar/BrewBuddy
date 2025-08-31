package com.example.brewbuddy.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brewbuddy.R
import com.example.brewbuddy.adapter.CartAdapter
import com.example.brewbuddy.databinding.ActivityCartBinding
import com.example.brewbuddy.helper.ChangeNumberItemsListener
import com.example.brewbuddy.helper.ManagmentCart

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        binding.apply {
            listView.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)

            listView.adapter = CartAdapter(
                managementCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }

                }
            )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percentTax=0.02
        val delivery = 15
        tax=Math.round(managementCart.getTotalFee()*percentTax*100)/100.0
        val total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100.0
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100.0

        binding.apply {
            subtotalPrice.text = "₹$itemTotal"
            taxPrice.text = "₹$tax"
            deliveryPrice.text = "₹$delivery"
            totalPrice.text = "₹$total"
        }
    }
}