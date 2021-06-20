package com.example.storemanagement.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.storemanagement.R
import com.example.storemanagement.utilities.Constants

class SplashActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        val handler=Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if (userId>0){
                startActivity(MainActivity.newIntent(this))
                finish()
            }else{
                startActivity(LoginActivity.newIntent(this))
                finish()
            }
        },1000)
    }
}