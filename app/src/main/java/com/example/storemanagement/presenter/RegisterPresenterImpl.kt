package com.example.storemanagement.presenter

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.contractor.RegisterPresenter
import com.example.storemanagement.contractor.RegisterView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.RegisterRequest
import com.example.storemanagement.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenterImpl:RegisterPresenter {

    private var view:RegisterView?=null

    override fun registerView(view: RegisterView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun registerUser(req:RegisterRequest){
        view?.showLoading()
        RestClient.getApiService()
                .registerUser(req)
                .enqueue(object :Callback<RegisterResponse>{
                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        view?.hideLoading()
                        view?.showError(t.message?:"Unknown Error")
                    }

                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        view?.hideLoading()
                        if (response.isSuccessful){
                            response.body()?.let {
                                view?.showResponseMessage(it.responseMessage)
                            }
                        }
                    }
                })
    }
}