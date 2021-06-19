package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class Product(
        @SerializedName("productId") val productId:String?,
        @SerializedName("name") val name:String?,
        @SerializedName("price") val price:String?
) {
}