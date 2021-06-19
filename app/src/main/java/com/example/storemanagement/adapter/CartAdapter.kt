package com.example.storemanagement.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storemanagement.model.Product
import com.example.storemanagement.R
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter:BaseAdapter<Product,CartAdapter.ProductViewHolder>() {

    val selectedItemList= mutableSetOf<Product>()

    class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view=getLayoutInflater(parent).inflate(R.layout.item_cart,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item=itemList.get(position)
        holder.itemView.tvPName.text=item.name
        holder.itemView.tvPrice.text=item.price

        val isSelected=selectedItemList.contains(item)
        holder.itemView.containerCartItem.isSelected=isSelected
        holder.itemView.ivArrow.isSelected=isSelected

        holder.itemView.setOnClickListener {
            if (isSelected){
                removeSelect(item)
            }else{
                addSelect(item)
            }
        }
    }

    private fun addSelect(item:Product){
        selectedItemList.add(item)
        val index=itemList.indexOf(item)
        notifyItemChanged(index)
    }

    private fun removeSelect(item:Product){
        selectedItemList.remove(item)
        val index=itemList.indexOf(item)
        notifyItemChanged(index)
    }
}