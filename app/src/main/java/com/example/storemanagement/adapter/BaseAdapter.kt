package com.example.storemanagement.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,VH:RecyclerView.ViewHolder>:RecyclerView.Adapter<VH>() {

    protected val itemList= mutableListOf<T>()

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setNewData(newItems:List<T>){
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }
}