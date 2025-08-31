package com.example.brewbuddy.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.brewbuddy.adapter.SizeAdapter
import com.example.brewbuddy.databinding.ActivityDetailBinding
import com.example.brewbuddy.domain.ItemModel
import com.example.brewbuddy.helper.ManagmentCart
import eightbitlab.com.blurview.RenderScriptBlur

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemModel
    private lateinit var managementCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        bundle()
        setBlurEffect()
        initSizeList()
    }

    private fun initSizeList() {
        val sizeList = ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()

        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.picMainCart)
    }

    private fun setBlurEffect() {
        val radius = 10f
        val decorView = this.window.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowsBackground=decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(this))
            .setFrameClearDrawable(windowsBackground)
            .setBlurRadius(radius)

        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true

    }

    private fun bundle() {
        binding.apply {
            item=intent.getSerializableExtra("object") as ItemModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMainCart)

            titleTxtCart.text = item.title
            descriptionTxt.text = item.description
            priceTxtCart.text = "₹${item.price}"
            ratingTxt.text = item.rating.toString()
            extraTxtCart.text = item.extra
            addToCartBtn.setOnClickListener {
                item.numberInCart = numberItemTxt.text.toString().toInt()
                managementCart.insertItems(item)
            }
            backBtn.setOnClickListener { finish() }
            plusCart.setOnClickListener {
                numberItemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }
            minusCart.setOnClickListener {
                if(item.numberInCart>0){
                    numberItemTxt.text = (item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}