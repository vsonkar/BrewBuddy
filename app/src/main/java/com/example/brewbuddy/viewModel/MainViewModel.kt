package com.example.brewbuddy.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.brewbuddy.domain.CategoryModel
import com.example.brewbuddy.domain.ItemModel
import com.example.brewbuddy.repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemModel>> {
        return repository.loadPopular()

    }

    fun loadSpecial(): LiveData<MutableList<ItemModel>> {
        return repository.loadSpecial()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemModel>>{
        return repository.loadCategoryItems(categoryId)
    }
}
