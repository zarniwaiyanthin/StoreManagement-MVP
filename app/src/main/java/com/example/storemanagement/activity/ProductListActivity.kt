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
import com.example.storemanagement.viewmodel.RemoveProductViewModel
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity:BaseActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var removeProductViewModel: RemoveProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productViewModel= ProductViewModel()
        removeProductViewModel= RemoveProductViewModel()

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

    private fun removeProduct(productId:Int,customerId:Int){
        removeProductViewModel.removeProduct(productId=productId,customerId = customerId)

        removeProductViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        removeProductViewModel.isLoading.observe(this, Observer {
            if(it){
                //todo: show loading
            }else{
                //todo: hide loading
            }
        })

        removeProductViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}