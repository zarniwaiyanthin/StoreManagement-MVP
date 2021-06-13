package com.example.storemanagement.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.CustomerAdapter
import com.example.storemanagement.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_customer_list.*

class CustomerListActivity:BaseActivity() {
    private lateinit var customerViewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        val userAdapter= CustomerAdapter()
        rvCustomer.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCustomer.adapter=userAdapter

        customerViewModel= CustomerViewModel()

        customerViewModel.customerList.observe(this, Observer {customerList->
            userAdapter.setData(customerList)
        })

        customerViewModel.isLoading.observe(this, Observer{ isLoading->
            if (isLoading){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        customerViewModel.error.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }
}