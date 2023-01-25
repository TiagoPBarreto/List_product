package com.teste.list_product.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teste.list_product.models.Product
import com.teste.list_product.utils.DATABASE_NAME

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("select * from product_table order by productId ASC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("UPDATE product_table SET productName = :productName, productType = :productType, productCode = :productCode," +
            "productBrand = :productBrand,productPlace = :productPlace, productToWalk = :productToWalk, productRoom = :productRoom WHERE productId = :id")
    suspend fun  update(id : Int?, productName:String?,productType: String?, productCode:Int?, productBrand:String?,
                        productPlace:String?, productToWalk:String?, productRoom:String?)


}