package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.contractor.AddCustomerView
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.presenter.AddCustomerPresenterImpl
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.presenter.CustomerPresenterImpl
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity:BaseActivity(),AddCustomerView{

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,AddCustomerActivity::class.java)
        }
    }

    private lateinit var addCustomerPresenterImpl: AddCustomerPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        addCustomerPresenterImpl= AddCustomerPresenterImpl()
        addCustomerPresenterImpl.registerView(this)

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        btnAddCustomer.setOnClickListener {

            val name=etCustomerName.text.toString()
            val phone=etPh.text.toString()

            if (userId>0){
                val req=AddCustomerRequest(
                        name = name,
                        phoneNo = phone,
                        userId = userId
                )
                addCustomerPresenterImpl.addCustomer(req)
            }
        }

        tbAddCustomer.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addCustomerPresenterImpl.unregisterView()
    }

    override fun showResponseMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar.visibility=View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility=View.INVISIBLE
    }
}