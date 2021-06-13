package com.example.storemanagement.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storemanagement.adapter.ProductAdapter
import com.example.storemanagement.R
import com.example.storemanagement.model.ProductListResponse
import com.example.storemanagement.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity:BaseActivity() {

    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productViewModel= ProductViewModel()

        val productAdapter= ProductAdapter()
        rvProduct.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvProduct.adapter=productAdapter

        productViewModel.productList.observe(this, Observer { productList->
            productAdapter.setData(productList)
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

    }
}