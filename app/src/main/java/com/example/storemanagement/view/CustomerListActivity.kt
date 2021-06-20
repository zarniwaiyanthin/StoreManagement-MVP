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
import com.example.storemanagement.R
import com.example.storemanagement.adapter.CustomerAdapter
import com.example.storemanagement.contractor.CustomerView
import com.example.storemanagement.listener.CustomerListListener
import com.example.storemanagement.listener.SwipeToDeleteListener
import com.example.storemanagement.model.Customer
import com.example.storemanagement.utilities.Constants
import com.example.storemanagement.presenter.CustomerPresenterImpl
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.activity_customer_list.progressBar

class CustomerListActivity:BaseActivity(),CustomerListListener ,CustomerView{

    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,CustomerListActivity::class.java)
        }
    }

    private lateinit var customerPresenterImpl: CustomerPresenterImpl
    private lateinit var customerAdapter:CustomerAdapter
    private var userId:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        customerAdapter= CustomerAdapter(this)
        rvCustomer.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCustomer.adapter=customerAdapter

        customerPresenterImpl= CustomerPresenterImpl()
        customerPresenterImpl.registerView(this)

        val pref=getSharedPreferences(Constants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
        userId=pref.getInt(Constants.KEY_USER_ID,-1)

        customerPresenterImpl.getCustomerList(userId!!)

        val swipeToDeleteListener=object :SwipeToDeleteListener(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val item=customerAdapter.getItemAt(position)
                item.id?.let { removeCustomer(it) }
                customerAdapter.deleteItem(position)
            }
        }

        val itemTouchHelper=ItemTouchHelper(swipeToDeleteListener)
        itemTouchHelper.attachToRecyclerView(rvCustomer)

        tbCustomerList.setNavigationOnClickListener {
            onBackPressed()
        }

        tbCustomerList.setOnMenuItemClickListener {
            startActivity(AddCustomerActivity.newIntent(this))
            true
        }
    }

    override fun onStart() {
        super.onStart()
        customerPresenterImpl.getCustomerList(userId!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        customerPresenterImpl.unregisterView()
    }

    private fun removeCustomer(customerId:Int){
        customerPresenterImpl.removeCustomer(customerId)
    }

    override fun onCustomerClick(item: Customer) {
        val intent=CartActivity.newIntent(this)
        intent.putExtra("id",item.id)
        startActivity(intent)
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

            customerPresenterImpl.getCustomerList(userId)
        }
    }

    override fun returnCustomerList(customerList: List<Customer>) {
        customerAdapter.setNewData(customerList)
    }
}