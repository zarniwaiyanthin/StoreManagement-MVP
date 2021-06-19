package com.example.storemanagement.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity:BaseActivity(){

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,AddCustomerActivity::class.java)
        }
    }

    private lateinit var customerViewModel:CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        customerViewModel= CustomerViewModel()

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
                customerViewModel.addCustomer(req)
            }
        }

        customerViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        customerViewModel.isLoading.observe(this, Observer {
            if (it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        customerViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        tbAddCustomer.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}