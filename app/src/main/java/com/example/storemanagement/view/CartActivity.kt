package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.R
import com.example.storemanagement.adapter.CartAdapter
import com.example.storemanagement.contractor.CartView
import com.example.storemanagement.data.request.Add2CartRequest
import com.example.storemanagement.model.Product
import com.example.storemanagement.presenter.CartPresenterImpl
import com.example.storemanagement.utilities.Constants
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.progressBar

class CartActivity:BaseActivity(),CartView {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,CartActivity::class.java)
        }
    }

    private lateinit var cartPresenterImpl: CartPresenterImpl
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartPresenterImpl= CartPresenterImpl()
        cartPresenterImpl.registerView(this)

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        val customerId=Intent().getIntExtra("id",1)

        cartAdapter= CartAdapter()
        rvCart.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCart.adapter=cartAdapter

        cartPresenterImpl.getProductList(userId)

        btnDone.setOnClickListener {
            val productIdList= mutableListOf<Int>()
            for (product in cartAdapter.selectedItemList){
                productIdList.add(product.productId!!.toInt())
            }
            val req=Add2CartRequest(
                    customerId = customerId,
                    productIds = productIdList
            )

            chooseProduct(req)
        }

        tbCart.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cartPresenterImpl.unregisterView()
    }

    private fun chooseProduct(req:Add2CartRequest){
        cartPresenterImpl.add2Cart(req)
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

    override fun returnProductList(productList: List<Product>) {
        cartAdapter.setNewData(productList)
    }
}