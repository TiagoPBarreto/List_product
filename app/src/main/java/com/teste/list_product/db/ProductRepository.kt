package com.teste.list_product.db

import androidx.lifecycle.LiveData
import com.teste.list_product.models.Product

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun  insert(product: Product){
        productDao.insert(product)
    }
    suspend fun delete(product: Product){
        productDao.delete(product)
    }
    suspend fun update(product: Product){
        productDao.update(product.productId,product.productName,product.productType,product.productCode,
            product.productBrand,product.productPlace,product.productToWalk,product.productRoom)
    }
}