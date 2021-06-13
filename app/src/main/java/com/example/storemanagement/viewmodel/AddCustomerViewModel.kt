package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.model.AddCustomerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCustomerViewModel:BaseViewModel() {
    val isLoading= MutableLiveData<Boolean>()
    val error= MutableLiveData<String>()
    val addedSuccess= MutableLiveData<String>()

    fun addCustomer(req:AddCustomerRequest){
        isLoading.value=true
        RestClient.getApiService()
                .addCustomer(req)
                .enqueue(object :Callback<AddCustomerResponse>{
                    override fun onFailure(call: Call<AddCustomerResponse>, t: Throwable) {
                        isLoading.value=false
                        error.value=t.message?:"Unknown Error"
                    }

                    override fun onResponse(call: Call<AddCustomerResponse>, response: Response<AddCustomerResponse>) {
                        if (response.isSuccessful){
                            isLoading.value=false
                            response.body()?.let {
                                addedSuccess.value=it.responseMessage
                                error.value=it.error?.firstOrNull()?.errorMessage?:"Unknown Error"
                            }
                        }
                    }
                })
    }
}