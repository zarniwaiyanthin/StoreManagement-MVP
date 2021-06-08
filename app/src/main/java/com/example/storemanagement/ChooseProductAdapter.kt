package com.example.storemanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_choose_product.view.*

class ChooseProductAdapter:RecyclerView.Adapter<ChooseProductAdapter.MyViewHolder>() {
    private val itemList= mutableListOf<Product>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_choose_product,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=itemList.get(position)
        holder.itemView.tvPName.text=item.productName
        holder.itemView.tvPrice.text=item.price
    }

    fun setData(newData:List<Product>){
        itemList.clear()
        itemList.addAll(newData)
        notifyDataSetChanged()
    }
}