package com.example.storemanagement.contractor

import android.view.View
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.model.Customer
import com.example.storemanagement.model.CustomerListResponse

interface CustomerView{
    fun showResponseMessage(message:String?)
    fun showError(error:String)
    fun showLoading()
    fun hideLoading()
    fun delete(isDelete:Boolean?=false)
    fun returnCustomerList(customerList:List<Customer>)
}

interface CustomerPresenter{
    fun getCustomerList(userId:Int)
    fun removeCustomer(customerId:Int)
    fun registerView(view:CustomerView)
    fun unregisterView()
}