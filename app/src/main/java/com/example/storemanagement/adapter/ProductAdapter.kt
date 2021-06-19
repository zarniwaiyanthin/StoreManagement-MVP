package com.example.storemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storemanagement.model.Product
import com.example.storemanagement.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter:BaseAdapter<Product,ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item=itemList.get(position)
        holder.itemView.tvProductName.text=item.name
        holder.itemView.tvProductPrice.text=item.price
    }
}