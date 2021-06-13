package com.example.storemanagement.data.request

import com.google.gson.annotations.SerializedName

class AddProductRequest(
        @SerializedName("name") val name:String?,
        @SerializedName("price") val price:Int?,
        @SerializedName("userId") val userId:Int?
) {
}