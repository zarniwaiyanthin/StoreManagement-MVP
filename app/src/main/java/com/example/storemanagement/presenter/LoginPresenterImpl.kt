package com.example.storemanagement.presenter

import androidx.lifecycle.MutableLiveData
import com.example.storemanagement.contractor.LoginPresenter
import com.example.storemanagement.contractor.LoginView
import com.example.storemanagement.data.remote.RestClient
import com.example.storemanagement.data.request.LoginRequest
import com.example.storemanagement.model.LoginResponse
import com.example.storemanagement.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenterImpl:LoginPresenter {

    private var view:LoginView?=null

    override fun registerView(view: LoginView) {
        this.view=view
    }

    override fun unregisterView() {
        this.view=null
    }

    override fun login(req: LoginRequest){
        view?.showLoading()
        view?.returnUser(User(2))
        return
        RestClient.getApiService()
            .login(req)
            .enqueue(object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    view?.hideLoading()
                    view?.showError(t.message?:"Unknown Error")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful){
                        view?.hideLoading()
                        response.body()?.let {body->
                            view?.returnUser(body.data?: User())
                        }
                    }
                }
            })
    }
}