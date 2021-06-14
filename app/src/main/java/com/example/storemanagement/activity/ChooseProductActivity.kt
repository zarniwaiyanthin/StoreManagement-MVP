package com.example.storemanagement.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.ChooseProductAdapter
import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.model.Product
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.Add2CartViewModel
import com.example.storemanagement.viewmodel.AddProductViewModel
import com.example.storemanagement.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_choose_product.*

class ChooseProductActivity:BaseActivity() {

    private lateinit var add2CartViewModel: Add2CartViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_product)

        add2CartViewModel= Add2CartViewModel()
        productViewModel= ProductViewModel()

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1
        )
        val chooseProductAdapter= ChooseProductAdapter()
        rvChooseProduct.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvChooseProduct.adapter=chooseProductAdapter

        productViewModel.productList.observe(this, Observer {
            if (userId>0){
                chooseProductAdapter.setData(it)
            }
        })

    }

    private fun chooseProduct(req:Add2CartRequest){
        add2CartViewModel.add2Cart(req)

        add2CartViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        add2CartViewModel.isLoading.observe(this, Observer {
            if (it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        add2CartViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}