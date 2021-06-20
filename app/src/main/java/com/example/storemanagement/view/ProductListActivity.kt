package com.example.storemanagement.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storemanagement.adapter.ProductAdapter
import com.example.storemanagement.R
import com.example.storemanagement.contractor.ProductView
import com.example.storemanagement.listener.ProductListListener
import com.example.storemanagement.listener.SwipeToDeleteListener
import com.example.storemanagement.model.Product
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.presenter.ProductPresenterImpl
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.activity_product_list.progressBar

class ProductListActivity:BaseActivity(),ProductListListener,ProductView {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,ProductListActivity::class.java)
        }
    }

    private lateinit var productPresenterImpl: ProductPresenterImpl
    private lateinit var productAdapter:ProductAdapter
    private var userId:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productPresenterImpl= ProductPresenterImpl()
        productPresenterImpl.registerView(this)

        productAdapter= ProductAdapter(this)
        rvProduct.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvProduct.adapter=productAdapter

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        userId=pref.getInt(Constants.KEY_USER_ID,-1)

        productPresenterImpl.getProductList(userId!!)

        tbProductList.setNavigationOnClickListener {
            onBackPressed()
        }

        tbProductList.setOnMenuItemClickListener {
            startActivity(AddProductActivity.newIntent(this))
            true
        }

        val swipeToDeleteListener=object :SwipeToDeleteListener(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val item=productAdapter.getItemAt(position)
                removeProduct(item.productId!!.toInt())
                productAdapter.deleteItem(position)
            }
        }

        val itemTouchHelper=ItemTouchHelper(swipeToDeleteListener)
        itemTouchHelper.attachToRecyclerView(rvProduct)

    }

    override fun onStart() {
        super.onStart()
        productPresenterImpl.getProductList(userId!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        productPresenterImpl.unregisterView()
    }

    private fun removeProduct(productId:Int){
        productPresenterImpl.removeProduct(productId=productId)
    }

    override fun onProductClick(item: Product) {
        TODO("Not yet implemented")
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

    override fun delete(isDelete: Boolean?) {
        if (isDelete!!){
            val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
            val userId=pref.getInt(Constants.KEY_USER_ID,-1)

            productPresenterImpl.getProductList(userId)
        }
    }

    override fun returnProductList(productList: List<Product>) {
        productAdapter.setNewData(productList)
    }
}