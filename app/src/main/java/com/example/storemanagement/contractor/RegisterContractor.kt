package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.RegisterRequest

interface RegisterView{
    fun showLoading()
    fun hideLoading()
    fun showError(error:String)
    fun showResponseMessage(message:String?)
}

interface RegisterPresenter{
    fun registerUser(req:RegisterRequest)
    fun registerView(view:RegisterView)
    fun unregisterView()
}