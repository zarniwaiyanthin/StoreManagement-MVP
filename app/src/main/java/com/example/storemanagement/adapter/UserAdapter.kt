package com.example.storemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storemanagement.R
import com.example.storemanagement.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter:RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    private val itemList= mutableListOf<User>()
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_user,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=itemList.get(position)
        holder.itemView.tvUserName.text=item.userName
        holder.itemView.tvPh.text=item.phone
    }

    fun setData(newData:List<User>){
        itemList.clear()
        itemList.addAll(newData)
        notifyDataSetChanged()
    }
}