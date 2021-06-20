package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.AddProductRequest

interface AddProductView{
    fun showResponseMessage(message:String?)
    fun showError(error:String)
    fun showLoading()
    fun hideLoading()
}

interface AddProductPresenter{
    fun addProduct(req: AddProductRequest)
    fun registerView(view:AddProductView)
    fun unregisterView()
}