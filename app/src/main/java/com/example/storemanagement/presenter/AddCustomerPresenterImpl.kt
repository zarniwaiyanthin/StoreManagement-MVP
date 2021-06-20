package com.example.storemanagement.presenter

import com.example.storemanagement.contractor.AddCustomerPresenter
import com.example.storemanagement.contractor.AddCustomerView
import com.example.storemanagement.contractor.CustomerView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.model.AddCustomerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCustomerPresenterImpl:AddCustomerPresenter {

    private var view: AddCustomerView?=null

    override fun registerView(view: AddCustomerView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun addCustomer(req: AddCustomerRequest) {
        view?.showLoading()
        RestClient.getApiService()
            .addCustomer(req)
            .enqueue(object : Callback<AddCustomerResponse> {
                override fun onFailure(call: Call<AddCustomerResponse>, t: Throwable) {
                    view?.hideLoading()
                    view?.showError(t.message?:"Unknown Error")
                }

                override fun onResponse(call: Call<AddCustomerResponse>, response: Response<AddCustomerResponse>) {
                    if (response.isSuccessful){
                        view?.hideLoading()
                        response.body()?.let {
                            view?.showResponseMessage(it.responseMessage)
                        }
                    }
                }
            })
    }
}