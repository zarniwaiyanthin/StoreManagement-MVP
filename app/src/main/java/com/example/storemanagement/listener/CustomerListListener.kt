package com.example.storemanagement.listener

import com.example.storemanagement.model.Customer

interface CustomerListListener {
    fun onCustomerClick(item:Customer)
}