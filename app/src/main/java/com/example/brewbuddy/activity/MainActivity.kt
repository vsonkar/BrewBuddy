package com.example.brewbuddy.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brewbuddy.R
import com.example.brewbuddy.adapter.CategoryAdapter
import com.example.brewbuddy.adapter.PopularAdapter
import com.example.brewbuddy.adapter.SpecialAdapter
import com.example.brewbuddy.databinding.ActivityMainBinding
import com.example.brewbuddy.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initPopular()
        initSpecial()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initSpecial() {
        binding.progressBarSpecial.visibility = View.VISIBLE
        viewModel.loadSpecial().observe(this){
            binding.recyclerViewSpecial.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.progressBarSpecial.visibility = View.GONE
            binding.recyclerViewSpecial.adapter = SpecialAdapter(it)
        }
        viewModel.loadSpecial()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.loadPopular().observe(this){
            binding.recyclerViewPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.progressBarPopular.visibility = View.GONE
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.loadCategory().observe(this) { list ->
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.progressBarCategory.visibility = View.GONE
            binding.recyclerViewCategory.adapter = CategoryAdapter(list)
        }
    }
}