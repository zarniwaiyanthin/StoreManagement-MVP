package com.example.storemanagement.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.AddProductViewModel
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity:BaseActivity() {

    private lateinit var addProductViewModel: AddProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        addProductViewModel= AddProductViewModel()

        val name=etProductName.text.toString()
        val price=etPrice.text.toString().toInt()

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        btnAddProduct.setOnClickListener {
            if (userId>0){
                val req=AddProductRequest(
                        name = name,
                        price = price,
                        userId = userId
                )
                addProductViewModel.addProduct(req)
            }
        }

        addProductViewModel.addedSuccess.observe(this, Observer { addedSuccess->
            Toast.makeText(this, addedSuccess, Toast.LENGTH_SHORT).show()
        })

        addProductViewModel.isLoading.observe(this, Observer { isLoading->
            if (isLoading){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        addProductViewModel.error.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }
}