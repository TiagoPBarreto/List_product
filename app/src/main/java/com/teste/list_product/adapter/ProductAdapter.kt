package com.teste.list_product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.teste.list_product.R
import com.teste.list_product.models.Product

class ProductAdapter(private val context: Context, val listener:ProductsItemClickListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val ProductsList = ArrayList<Product>()
    private val fullList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = ProductsList[position]
        holder.name.text = currentProduct.productName
        holder.code.text = currentProduct.productCode.toString()
        holder.toWalk.text = currentProduct.productToWalk
        holder.brand.text = currentProduct.productBrand
        holder.place.text = currentProduct.productPlace
        holder.room.text = currentProduct.productRoom
        holder.type.text = currentProduct.productType

        holder.product_layoyt.setOnClickListener {
            listener.onItemClicked(ProductsList[holder.adapterPosition])
        }
        holder.product_layoyt.setOnLongClickListener {
            listener.onLongItemClicked(ProductsList[holder.adapterPosition],holder.product_layoyt)
            true
        }

    }

    override fun getItemCount(): Int {
        return ProductsList.size
    }

    fun updateList(newList : List<Product>){

        fullList.clear()
        fullList.addAll(newList)

        ProductsList.clear()
        ProductsList.addAll(newList)
        notifyDataSetChanged()
    }
    fun filterList(search : String){
        for (item in fullList){
            if (item.productToWalk?.lowercase()?.contains(search.lowercase()) == true ||
                item.productName?.lowercase()?.contains(search.lowercase()) == true ||
                item.productRoom?.lowercase()?.contains(search.lowercase()) == true){
                ProductsList.add(item)
            }


        }
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val product_layoyt = itemView.findViewById<CardView>(R.id.cardViewProduct)
        val name = itemView.findViewById<TextView>(R.id.textName)
        val code = itemView.findViewById<TextView>(R.id.textCode)
        val brand = itemView.findViewById<TextView>(R.id.textBrand)
        val type = itemView.findViewById<TextView>(R.id.textType)
        val place = itemView.findViewById<TextView>(R.id.textPlace)
        val toWalk = itemView.findViewById<TextView>(R.id.textToWalk)
        val room = itemView.findViewById<TextView>(R.id.textRoom)

    }
    interface ProductsItemClickListener{

        fun onItemClicked(product: Product)
        fun onLongItemClicked(product: Product, cardView: CardView)


    }
}