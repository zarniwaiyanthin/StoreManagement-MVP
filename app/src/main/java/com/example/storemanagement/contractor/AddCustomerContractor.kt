package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.AddCustomerRequest

interface AddCustomerView{
    fun showResponseMessage(message:String?)
    fun showError(error:String)
    fun showLoading()
    fun hideLoading()
}

interface AddCustomerPresenter{
    fun registerView(view:AddCustomerView)
    fun unregisterView()
    fun addCustomer(req: AddCustomerRequest)
}