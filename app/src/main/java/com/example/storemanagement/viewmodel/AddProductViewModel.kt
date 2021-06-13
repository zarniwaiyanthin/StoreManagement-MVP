package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.model.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductViewModel:BaseViewModel() {
    val isLoading=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()
    val addedSuccess=MutableLiveData<String>()

    fun addProduct(req:AddProductRequest){
        isLoading.value=true
        RestClient.getApiService()
                .addProduct(req)
                .enqueue(object :Callback<ProductListResponse>{
                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        isLoading.value=false
                        error.value=t.message?:"Unknown Error"
                    }

                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if (response.isSuccessful){
                            isLoading.value=false
                            response.body()?.let {body->
                                addedSuccess.value=body.responseMessage?:"N/A"
                                error.value=body.error?.firstOrNull()?.errorMessage?:"Unknown Error"
                            }
                        }
                    }
                })
    }
}