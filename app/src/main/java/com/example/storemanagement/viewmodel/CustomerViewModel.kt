package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.model.Customer
import com.example.storemanagement.model.CustomerListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel:BaseViewModel() {
    val isLoading= MutableLiveData<Boolean>()
    val error= MutableLiveData<String>()
    val customerList= MutableLiveData<List<Customer>>()

    fun getCustomerList(userId:Int){
        isLoading.value=true
        RestClient.getApiService()
            .getCustomerList(userId)
            .enqueue(object :Callback<CustomerListResponse>{
                override fun onFailure(call: Call<CustomerListResponse>, t: Throwable) {
                    isLoading.value=false
                    error.value=t.message?:"Unknown Error"
                }

                override fun onResponse(
                    call: Call<CustomerListResponse>,
                    response: Response<CustomerListResponse>
                ) {
                    if (response.isSuccessful){
                        isLoading.value=false
                        response.body()?.let {body->
                            customerList.value=body.data?: listOf()
                            error.value=body.error?.firstOrNull()?.errorMessage?:"Unknown Error"
                        }
                    }
                }
            })
    }
}