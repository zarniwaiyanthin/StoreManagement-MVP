package com.example.storemanagement.presenter

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.contractor.ProductPresenter
import com.example.storemanagement.contractor.ProductView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenterImpl:ProductPresenter {

    private var view:ProductView?=null

    override fun registerView(view: ProductView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun getProductList(userId:Int){
        view?.showLoading()
        RestClient.getApiService()
                .getProductList(userId)
                .enqueue(object :Callback<ProductListResponse>{
                    override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                        view?.hideLoading()
                        view?.showError(t.message?:"Unknown Error")
                    }

                    override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                        if(response.isSuccessful){
                            view?.hideLoading()
                            response.body()?.let { body->
                                view?.returnProductList(body.data?: listOf())
                            }
                        }
                    }
                })
    }

    override fun removeProduct(productId:Int){
        view?.showLoading()
        RestClient.getApiService()
                .removeProduct(productId=productId)
                .enqueue(object :Callback<RemoveResponse>{
                    override fun onFailure(call: Call<RemoveResponse>, t: Throwable) {
                        view?.hideLoading()
                        view?.showError(t.message?:"Unknown Error")
                    }

                    override fun onResponse(call: Call<RemoveResponse>, response: Response<RemoveResponse>) {
                        view?.hideLoading()
                        if (response.isSuccessful){
                            response.body()?.let {
                                view?.delete(true)
                                view?.showResponseMessage(it.responseMessage)
                            }
                        }else{
                            view?.delete(false)
                        }
                    }
                })
    }

    private fun getProductList()= mutableListOf<Product>(
        Product("1","aflasjf","12421"),
        Product("1","aflasjf","12421"),
        Product("1","aflasjf","12421"),
        Product("1","aflasjf","12421"),
        Product("1","aflasjf","12421")
    )
}