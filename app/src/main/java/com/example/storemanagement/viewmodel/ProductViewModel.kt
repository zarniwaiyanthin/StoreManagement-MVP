package com.example.storemanagement.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.model.Product
import com.example.storemanagement.model.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel:BaseViewModel() {
    val isLoading=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()
    val productList=MutableLiveData<List<Product>>()

    fun getProductList(userId:Int){
        isLoading.value=true
        RestClient.getApiService()
                .getProductList(userId)
                .enqueue(object :Callback<ProductListResponse>{
                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        isLoading.value=false
                        error.value=t.message?:"Unknown Error"
                    }

                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if(response.isSuccessful){
                            isLoading.value=false
                            response.body()?.let { body->
                                productList.value=body.data?: listOf()
                                error.value=body.error?.firstOrNull()?.errorMessage?:"Unknown Error"
                            }
                        }
                    }
                })
    }
}