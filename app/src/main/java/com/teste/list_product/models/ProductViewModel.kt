package com.teste.list_product.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.teste.list_product.db.ProductDatabase
import com.teste.list_product.db.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val allProducts : LiveData<List<Product>>

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        repository = ProductRepository(dao)
        allProducts = repository.allProducts
    }
    fun deleteProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }
    fun insertProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(product)
    }
    fun updateProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(product)
    }
}