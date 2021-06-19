package com.example.storemanagement.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.adapter.ProductAdapter
import com.example.storemanagement.R
import com.example.storemanagement.data.request.AddProductRequest
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity:BaseActivity() {

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,ProductListActivity::class.java)
        }
    }

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productViewModel= ViewModelProvider(this).get(ProductViewModel::class.java)

        val productAdapter= ProductAdapter()
        rvProduct.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvProduct.adapter=productAdapter

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        val userId=pref.getInt(Constants.KEY_USER_ID,-1)

        productViewModel.getProductList(userId)

        productViewModel.productList.observe(this, Observer { productList->
            productAdapter.setNewData(productList)
        })

        productViewModel.isLoading.observe(this, Observer { isLoading->
            if (isLoading){
                //todo: show loading
            }else{
                //todo:hide loading
            }
        })

        productViewModel.error.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        tbProductList.setNavigationOnClickListener {
            onBackPressed()
        }

        tbProductList.setOnMenuItemClickListener {
            startActivity(AddProductActivity.newIntent(this))
            true
        }

    }

    private fun removeProduct(productId:Int,customerId:Int){
        productViewModel.removeProduct(productId=productId,customerId = customerId)

        productViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        productViewModel.isLoading.observe(this, Observer {
            if(it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        productViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}