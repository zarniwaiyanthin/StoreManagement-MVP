package com.example.storemanagement.data.remote

import android.util.Log
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.data.request.LoginRequest
import com.example.storemanagement.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body req:LoginRequest):Call<LoginResponse>

    @POST("customer/list")
    fun getCustomerList(@Header("userId") userId:Int):Call<CustomerListResponse>

    @POST("product/list")
    fun getProductList(@Header("userId") userId: Int):Call<ProductListResponse>

    @POST("product/add")
    fun addProduct(@Body req:AddProductRequest):Call<ProductListResponse>

    @POST("customer/add")
    fun addCustomer(@Body req: AddCustomerRequest):Call<AddCustomerResponse>
}