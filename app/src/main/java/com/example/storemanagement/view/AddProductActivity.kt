package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.storemanagement.R
import com.example.storemanagement.contractor.AddProductView
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.presenter.AddProductPresenterImpl
import com.example.storemanagement.utilities.Constants
import kotlinx.android.synthetic.main.activity_add_customer.*
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_add_product.progressBar

class AddProductActivity:BaseActivity(),AddProductView {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,AddProductActivity::class.java)
        }
    }

    private lateinit var addProductPresenterImpl: AddProductPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        addProductPresenterImpl= AddProductPresenterImpl()
        addProductPresenterImpl.registerView(this)

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        btnAddProduct.setOnClickListener {

            val name=etProductName.text.toString()
            val price=etPrice.text.toString().toIntOrNull()
            
            if (userId>0){
                val req=AddProductRequest(
                        name = name,
                        price = price,
                        userId = userId
                )
                addProductPresenterImpl.addProduct(req)
            }
        }

        tbAddProduct.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addProductPresenterImpl.unregisterView()
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
        progressBar.visibility=View.INVISIBLE
    }
}