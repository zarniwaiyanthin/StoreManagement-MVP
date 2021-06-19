package com.example.storemanagement.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.CustomerAdapter
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.listener.CustomerListListener
import com.example.storemanagement.model.Customer
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.CustomerViewModel
import kotlinx.android.synthetic.main.activity_customer_list.*

class CustomerListActivity:BaseActivity(),CustomerListListener {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,CustomerListActivity::class.java)
        }
    }

    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        val userAdapter= CustomerAdapter(this)
        rvCustomer.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCustomer.adapter=userAdapter

        customerViewModel= ViewModelProvider(this).get(CustomerViewModel::class.java)

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        customerViewModel.getCustomerList(userId)

        customerViewModel.customerList.observe(this, Observer {customerList->
            userAdapter.setNewData(customerList)
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

        tbCustomerList.setNavigationOnClickListener {
            onBackPressed()
        }

        tbCustomerList.setOnMenuItemClickListener {
            startActivity(AddCustomerActivity.newIntent(this))
            true
        }
    }

    private fun removeCustomer(customerId:Int){
        customerViewModel.removeCustomer(customerId)

        customerViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        customerViewModel.isLoading.observe(this, Observer {
            if(it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        customerViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCustomerClick(item: Customer) {
        val intent=CartActivity.newIntent(this)
        intent.putExtra("id",item.id)
        startActivity(intent)
    }
}