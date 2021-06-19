package com.example.storemanagement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,VH:RecyclerView.ViewHolder>:RecyclerView.Adapter<VH>() {

    protected val itemList= mutableListOf<T>()

    protected fun getLayoutInflater(view:ViewGroup): LayoutInflater {
       val inflater =  LayoutInflater.from(view.context)
        return inflater
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setNewData(newItems:List<T>){
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }
}