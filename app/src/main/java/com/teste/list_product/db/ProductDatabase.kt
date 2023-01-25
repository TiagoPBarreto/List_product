package com.teste.list_product.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teste.list_product.models.Product
import com.teste.list_product.utils.DATABASE_NAME

@Database(entities = arrayOf(Product::class), version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun getProductDao() : ProductDao

    companion object{
        @Volatile
        private var INSTANCE : ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}