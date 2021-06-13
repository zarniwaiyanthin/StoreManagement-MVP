package com.example.storemanagement.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.ChooseProductAdapter
import kotlinx.android.synthetic.main.activity_choose_product.*

class ChooseProductActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_product)

        val chooseProductAdapter= ChooseProductAdapter()
        rvChooseProduct.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvChooseProduct.adapter=chooseProductAdapter
    }
}