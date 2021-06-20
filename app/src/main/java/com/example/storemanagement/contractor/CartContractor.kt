package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.model.Product

interface CartView{
    fun showResponseMessage(message:String?)
    fun showError(error:String)
    fun showLoading()
    fun hideLoading()
    fun returnProductList(productList:List<Product>)
}

interface CartPresenter{
    fun getProductList(userId:Int)
    fun add2Cart(req: Add2CartRequest)
    fun registerView(view:CartView)
    fun unregisterView()
}