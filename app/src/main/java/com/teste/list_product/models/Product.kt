package com.teste.list_product.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
@PrimaryKey(autoGenerate = true)
val productId: Int?,
@ColumnInfo()
val productName: String?,
@ColumnInfo()
val productType: String?,
@ColumnInfo()
val productCode: Int?,
@ColumnInfo()
val productBrand: String?,
@ColumnInfo()
val productPlace: String?,
@ColumnInfo()
val productToWalk: String?,
@ColumnInfo()
val productRoom: String?,

):java.io.Serializable


