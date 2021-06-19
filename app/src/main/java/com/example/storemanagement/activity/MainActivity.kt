package com.example.storemanagement.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storemanagement.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,MainActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCustomer.setOnClickListener {
            startActivity(CustomerListActivity.newIntent(this))
        }

        btnProduct.setOnClickListener {
            startActivity(ProductListActivity.newIntent(this))
        }
    }
}