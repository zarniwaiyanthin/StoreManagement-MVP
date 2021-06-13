package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class Customer(
    @SerializedName("id") val id:Int?,
    @SerializedName("name") val name:String?,
    @SerializedName("phoneNo") val phoneNo:String?
) {
}