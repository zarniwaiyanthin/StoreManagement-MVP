package com.example.storemanagement.presenter

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.contractor.CustomerPresenter
import com.example.storemanagement.contractor.CustomerView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.AddCustomerRequest
import com.example.storemanagement.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerPresenterImpl:CustomerPresenter {

    private var view:CustomerView?=null

    override fun registerView(view: CustomerView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun getCustomerList(userId:Int){
        view?.showLoading()
        RestClient.getApiService()
            .getCustomerList(userId)
            .enqueue(object :Callback<CustomerListResponse>{
                override fun onFailure(call: Call<CustomerListResponse>, t: Throwable) {
                    view?.hideLoading()
                    view?.showError(t.message?:"Unknown")
                }

                override fun onResponse(
                    call: Call<CustomerListResponse>,
                    response: Response<CustomerListResponse>
                ) {
                    if (response.isSuccessful){
                        view?.hideLoading()
                        response.body()?.let {body->
                            view?.returnCustomerList(body.data?: listOf())
                        }
                    }
                }
            })
    }

    override fun removeCustomer(customerId:Int){
        view?.showLoading()
        RestClient.getApiService()
                .removeCustomer(customerId)
                .enqueue(object:retrofit2.Callback<RemoveResponse>{
                    override fun onFailure(call: Call<RemoveResponse>, t: Throwable) {
                        view?.hideLoading()
                        view?.showError(t.message?:"Unknown Error")
                    }

                    override fun onResponse(call: Call<RemoveResponse>, response: Response<RemoveResponse>) {
                        view?.hideLoading()
                        if (response.isSuccessful){
                            response.body()?.let {
                                view?.showResponseMessage(it.responseMessage)
                                view?.delete(true)
                            }
                        }else{
                            view?.delete(false)
                        }
                    }
                })
    }

    private fun getCustomerList()= mutableListOf<Customer>(
        Customer(1,"aflasjf","12421"),
        Customer(2,"aflasjf","12421"),
        Customer(2,"aflasjf","12421"),
        Customer(3,"aflasjf","12421"),
        Customer(4,"aflasjf","12421")
    )
}