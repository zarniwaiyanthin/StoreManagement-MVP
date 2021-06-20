package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.contractor.LoginView
import com.example.storemanagement.data.request.LoginRequest
import com.example.storemanagement.model.User
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.presenter.LoginPresenterImpl
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressBar

class LoginActivity:BaseActivity(),LoginView {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,LoginActivity::class.java)
        }
    }

    private lateinit var loginPresenterImpl:LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenterImpl= LoginPresenterImpl()
        loginPresenterImpl.registerView(this)

        btnLogin.setOnClickListener {
            val name=etName.text.toString()
            val password=etPassword.text.toString()

            when{
                name.isBlank()-> Toast.makeText(this, "Please enter user name.", Toast.LENGTH_SHORT).show()
                password.isBlank()-> Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                else->{
                    val req=LoginRequest(
                            userName = name,
                            password = password
                    )
                    loginPresenterImpl.login(req)
                }
            }
        }

        tvRegister.setOnClickListener {
            startActivity(RegisterActivity.newIntent(this))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenterImpl.unregisterView()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressBar.visibility=View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun returnUser(user: User) {
        val userId=user.userId?:-1
        if (userId>0){
            val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(Constants.KEY_USER_ID,userId)
                .apply()
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }
}