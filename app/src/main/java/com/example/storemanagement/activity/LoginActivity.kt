package com.example.storemanagement.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.storemanagement.R
import com.example.storemanagement.data.request.LoginRequest
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.BaseViewModel
import com.example.storemanagement.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:BaseActivity() {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,LoginActivity::class.java)
        }
    }

    private lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel= LoginViewModel()

        btnLogin.setOnClickListener {
            val name=etName.text.toString()
            val password=etPassword.text.toString()

            //todo: validation

            val req=LoginRequest(
                userName = name,
                password = password
            )
            loginViewModel.login(req)
        }

        loginViewModel.isLoading.observe(this, Observer { isLoading->
            if (isLoading){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        loginViewModel.error.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        loginViewModel.user.observe(this, Observer { user->
            val userId=user.userId?:-1
            if (userId>0){
                val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .putInt(Constants.KEY_USER_ID,userId)
                    .apply()
                startActivity(MainActivity.newIntent(this))
                finish()
            }
        })
    }
}