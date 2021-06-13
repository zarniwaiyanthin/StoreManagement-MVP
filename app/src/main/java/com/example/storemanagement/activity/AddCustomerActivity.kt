package com.example.storemanagement.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.AddCustomerViewModel
import com.example.storemanagement.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity:BaseActivity(){

    private lateinit var addCustomerViewModel: AddCustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        addCustomerViewModel= AddCustomerViewModel()

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        val name=etCustomerName.text.toString()
        val phone=etPh.text.toString()

        btnAddCustomer.setOnClickListener {
            if (userId>0){
                val req=AddCustomerRequest(
                        name = name,
                        phoneNo = phone,
                        userId = userId
                )
                addCustomerViewModel.addCustomer(req)
            }
        }

        addCustomerViewModel.addedSuccess.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        addCustomerViewModel.isLoading.observe(this, Observer {
            if (it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        addCustomerViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}