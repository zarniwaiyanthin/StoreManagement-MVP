package com.example.storemanagement.presenter

import com.example.storemanagement.contractor.AddProductPresenter
import com.example.storemanagement.contractor.AddProductView
import com.example.storemanagement.contractor.ProductView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.model.ProductListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductPresenterImpl:AddProductPresenter {

    private var view: AddProductView?=null

    override fun registerView(view: AddProductView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun addProduct(req: AddProductRequest) {
        view?.showLoading()
        RestClient.getApiService()
            .addProduct(req)
            .enqueue(object : Callback<ProductListResponse> {
                override fun onFailure(call: Call<ProductListResponse>, t: Throwable) {
                    view?.hideLoading()
                    view?.showError(t.message?:"Unknown Error")
                }

                override fun onResponse(call: Call<ProductListResponse>, response: Response<ProductListResponse>) {
                    if (response.isSuccessful){
                        view?.hideLoading()
                        response.body()?.let {body->
                            view?.showResponseMessage(body.responseMessage?:"N/A")
                        }
                    }
                }
            })
    }
}