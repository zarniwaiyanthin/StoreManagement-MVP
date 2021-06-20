package com.example.storemanagement.contractor

import com.example.storemanagement.data.request.LoginRequest
import com.example.storemanagement.model.User

interface LoginView{
    fun showLoading()
    fun hideLoading()
    fun showError(error:String)
    fun returnUser(user:User)
}

interface LoginPresenter{
    fun login(req:LoginRequest)
    fun registerView(view:LoginView)
    fun unregisterView()
}