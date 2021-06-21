package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.contractor.RegisterView
import com.example.storemanagement.data.request.RegisterRequest
import com.example.storemanagement.presenter.RegisterPresenterImpl
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.progressBar

class RegisterActivity:BaseActivity(),RegisterView {
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,RegisterActivity::class.java)
        }
    }

    private lateinit var registerPresenterImpl: RegisterPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerPresenterImpl= RegisterPresenterImpl()
        registerPresenterImpl.registerView(this)

        btnRegister.setOnClickListener {

            val name=etName.text.toString()
            val password=etPassword.text.toString()
            val confirmPassword=etConfirmPassword.text.toString()

            when{
                name.isBlank()-> Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show()
                password.isBlank()-> Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                confirmPassword.isBlank()-> Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show()
                password!=confirmPassword-> Toast.makeText(this, "Password and confirm password must be match", Toast.LENGTH_SHORT).show()
                else->{
                    val req=RegisterRequest(
                            name = name,
                            password = password,
                            deviceToken = "Sample Device Token"
                    )
                    registerPresenterImpl.registerUser(req)
                }
            }
        }

        tvLogin.setOnClickListener {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        registerPresenterImpl.unregisterView()
    }

    override fun showResponseMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
}