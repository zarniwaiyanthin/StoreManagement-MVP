package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.model.CustomerListResponse
import com.example.storemanagement.model.Product
import com.example.storemanagement.model.ProductListResponse

interface ProductView{
    fun showResponseMessage(message:String?)
    fun showError(error:String)
    fun showLoading()
    fun hideLoading()
    fun delete(isDelete:Boolean?=false)
    fun returnProductList(productList:List<Product>)
}

interface ProductPresenter{
    fun getProductList(userId:Int)
    fun removeProduct(productId:Int)
    fun registerView(view:ProductView)
    fun unregisterView()
}