package com.teste.list_product.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.teste.list_product.R
import com.teste.list_product.adapter.ProductAdapter
import com.teste.list_product.databinding.ActivityMainBinding
import com.teste.list_product.db.ProductDatabase
import com.teste.list_product.models.Product
import com.teste.list_product.models.ProductViewModel

class MainActivity : AppCompatActivity(), ProductAdapter.ProductsItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ProductDatabase
    lateinit var viewModel: ProductViewModel
    lateinit var adapter: ProductAdapter
    lateinit var selectedProduct: Product
    private val updateProduct = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            val product = result.data?.getSerializableExtra("product")as? Product
            if (product != null){
                viewModel.updateProduct(product)
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ProductViewModel::class.java)
        viewModel.allProducts.observe(this){ list ->
            list?.let{
                adapter.updateList(list)
            }

        }
        database = ProductDatabase.getDatabase(this)
    }

    private fun initUI() {
        binding.recyProducts.setHasFixedSize(true)
        binding.recyProducts.layoutManager = StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL)
        adapter = ProductAdapter(this,this)
        binding.recyProducts.adapter = adapter
        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                val product = result.data?.getSerializableExtra("product")as? Product
                if (product != null){
                    viewModel.insertProduct(product)
                }
            }

        }
        binding.btnAddProducts.setOnClickListener {
            val intent = Intent(this,AddProduct::class.java)
            getContent.launch(intent)
        }
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    adapter.filterList(newText)
                }
                return true
            }

        })
    }

    override fun onItemClicked(product: Product) {
        val intent = Intent(this@MainActivity,AddProduct::class.java)
        intent.putExtra("current_Product",product)
        updateProduct.launch(intent)
    }

    override fun onLongItemClicked(product: Product, cardView: CardView) {
        selectedProduct = product
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {

    }


}