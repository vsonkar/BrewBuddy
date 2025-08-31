package com.example.brewbuddy.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brewbuddy.R
import com.example.brewbuddy.adapter.PopularAdapter
import com.example.brewbuddy.databinding.ActivityItemListBinding
import com.example.brewbuddy.viewModel.MainViewModel

class ItemListActivity : AppCompatActivity() {
    lateinit var binding : ActivityItemListBinding
    private val viewModel = MainViewModel()
    private var id: String=""
    private var title: String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBar.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemListActivity, Observer{
                listView.layoutManager=
                    GridLayoutManager(this@ItemListActivity, 2)
                listView.adapter= PopularAdapter(it)
                progressBar.visibility=View.GONE
            })
            backBtn.setOnClickListener { finish() }
        }
    }

    private fun getBundle() {
        id=intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryTxt.text = title
    }
}