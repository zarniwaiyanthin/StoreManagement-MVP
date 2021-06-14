package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.model.Add2CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Add2CartViewModel:BaseViewModel() {
    val isLoading=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()
    val responseMessage=MutableLiveData<String>()

    fun add2Cart(req:Add2CartRequest){
        isLoading.value=true
        RestClient.getApiService()
                .add2Cart(req)
                .enqueue(object :Callback<Add2CartResponse>{
                    override fun onFailure(call: Call<Add2CartResponse>, t: Throwable) {
                        isLoading.value=false
                        error.value=t.message?:"Unknown Error"
                    }

                    override fun onResponse(call: Call<Add2CartResponse>, response: Response<Add2CartResponse>) {
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