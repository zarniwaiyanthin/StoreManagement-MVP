package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.model.RemoveResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoveProductViewModel:BaseViewModel() {
    val isLoading=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()
    val responseMessage=MutableLiveData<String>()

    fun removeProduct(productId:Int,customerId:Int){
        isLoading.value=true
        RestClient.getApiService()
                .removeProduct(productId=productId,customerId = customerId)
                .enqueue(object :Callback<RemoveResponse>{
                    override fun onFailure(call: Call<RemoveResponse>, t: Throwable) {
                        isLoading.value=false
                        error.value=t.message?:"Unknown Error"
                    }

                    override fun onResponse(call: Call<RemoveResponse>, response: Response<RemoveResponse>) {
                        isLoading.value=false
                        if (response.isSuccessful){
                            response.body()?.let {
                                responseMessage.value=it.responseMessage
                                error.value=it.error?.firstOrNull()?.errorMessage?:"Unknown Error"
                            }
                        }
                    }
                })
    }
}