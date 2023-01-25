package com.teste.list_product.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.teste.list_product.databinding.ActivityAddProductBinding
import com.teste.list_product.db.ProductRepository
import com.teste.list_product.models.Product

class AddProduct : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var product: Product
    private lateinit var old_product: Product
    private lateinit var repository: ProductRepository
    var isUpdate = false

    private var productId = 0
    private var defaultName = ""
    private var defaultCode = ""
    private var defaultBrand = ""
    private var defaultType = ""
    private var defaultPlace = ""
    private var defaultToWalk = ""
    private var defaultRoom = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_product = intent.getSerializableExtra("current_Product") as Product
            binding.edName.setText(old_product.productName)
            old_product.productCode?.let { binding.edCode.setText(it) }
            binding.edBrand.setText(old_product.productBrand)
            binding.edRoom.setText(old_product.productRoom)
            binding.edToWalk.setText(old_product.productToWalk)
            binding.edType.setText(old_product.productType)
            binding.edPlace.setText(old_product.productPlace)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.btnSave.setOnClickListener {
            val name = binding.edName.text.toString()
            val code = binding.edCode.text.toString()
            val brand = binding.edBrand.text.toString()
            val room = binding.edRoom.text.toString()
            val toWalk = binding.edToWalk.text.toString()
            val type = binding.edType.text.toString()
            val place = binding.edPlace.text.toString()

            if (name.isNotEmpty() || code.isNotEmpty() || brand.isNotEmpty() || room.isNotEmpty() ||
                toWalk.isNotEmpty() || type.isNotEmpty() || place.isNotEmpty()
            ) {
                if (isUpdate) {
                    product = Product(
                        old_product.productId,
                        name,
                        type,
                        old_product.productCode,
                        brand,
                        place,
                        toWalk,
                        room,
                    )
                } else {
                    product = Product(
                        null, name, type, null, brand, place, toWalk, room
                    )
                }

                val intent = Intent()
                intent.putExtra("product", product)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddProduct, "Favor informar todos os campos", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            binding.btnDelete.setOnClickListener {
                onBackPressed()
            }
        }

    }
}


