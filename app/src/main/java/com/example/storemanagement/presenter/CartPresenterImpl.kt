package com.example.storemanagement.presenter

import com.example.storemanagement.contractor.CartPresenter
import com.example.storemanagement.contractor.CartView
import com.example.storemanagement.contractor.ProductView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.model.Add2CartResponse
import com.example.storemanagement.model.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenterImpl:CartPresenter {

    private var view: CartView?=null

    override fun registerView(view: CartView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun getProductList(userId: Int) {
        view?.showLoading()
        RestClient.getApiService()
            .getProductList(userId)
            .enqueue(object : Callback<ProductListResponse> {
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

    override fun add2Cart(req: Add2CartRequest) {
        view?.showLoading()
        RestClient.getApiService()
            .add2Cart(req)
            .enqueue(object :Callback<Add2CartResponse>{
                override fun onFailure(call: Call<Add2CartResponse>, t: Throwable) {
                    view?.hideLoading()
                    view?.showError(t.message?:"Unknown Error")
                }

                override fun onResponse(call: Call<Add2CartResponse>, response: Response<Add2CartResponse>) {
                    view?.hideLoading()
                    if (response.isSuccessful){
                        response.body()?.let {
                            view?.showResponseMessage(it.responseMessage)
                            view?.showError(it.error?.firstOrNull()?.errorMessage?:"Unknown Error")
                        }
                    }
                }
            })
    }
}