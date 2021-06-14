package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class CartData(
      @SerializedName("customerId") val customerId:Int?,
      @SerializedName("name") val name:String?,
      @SerializedName("phoneNo") val phoneNo:String?,
      @SerializedName("productList") val productList:List<Product>?
) {
}