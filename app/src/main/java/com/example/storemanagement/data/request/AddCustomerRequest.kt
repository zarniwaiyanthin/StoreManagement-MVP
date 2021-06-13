package com.example.storemanagement.data.request

import com.google.gson.annotations.SerializedName

class AddCustomerRequest(
        @SerializedName("name") val name:String?,
        @SerializedName("phoneNo") val phoneNo:String?,
        @SerializedName("userId") val userId:Int?
) {
}