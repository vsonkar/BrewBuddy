package com.example.brewbuddy.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brewbuddy.domain.CategoryModel
import com.example.brewbuddy.domain.ItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase =
        FirebaseDatabase.getInstance("https://brewbuddy-f9022-default-rtdb.asia-southeast1.firebasedatabase.app")

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun loadPopular(): LiveData<MutableList<ItemModel>> {
        val listData = MutableLiveData<MutableList<ItemModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value=list
            }
            override fun onCancelled(error: DatabaseError) {
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun loadSpecial(): LiveData<MutableList<ItemModel>> {
        val listData = MutableLiveData<MutableList<ItemModel>>()
        val ref = firebaseDatabase.getReference("Special")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value=list
            }
            override fun onCancelled(error: DatabaseError) {
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun loadCategoryItems(categoryId: String): LiveData<MutableList<ItemModel>>{
        val itemsLiveData = MutableLiveData<MutableList<ItemModel>>()
        val ref= firebaseDatabase.getReference("Items")
        val query: Query=ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var list= mutableListOf<ItemModel>()
                for(child in snapshot.children){
                    val item=child.getValue(ItemModel::class.java)
                    item?.let { list.add(it) }
                }
                itemsLiveData.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return itemsLiveData
    }
}