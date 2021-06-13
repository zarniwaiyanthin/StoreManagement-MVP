package com.example.storemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storemanagement.R
import com.example.storemanagement.model.Customer
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerAdapter:RecyclerView.Adapter<CustomerAdapter.MyViewHolder>() {
    private val itemList= mutableListOf<Customer>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_customer,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=itemList.get(position)
        holder.itemView.tvUserName.text=item.name
        holder.itemView.tvPh.text=item.phoneNo
    }

    fun setData(newData:List<Customer>){
        itemList.clear()
        itemList.addAll(newData)
        notifyDataSetChanged()
    }
}