package com.example.storemanagement.data.request

import com.google.gson.annotations.SerializedName

class RegisterRequest(
        @SerializedName("name") val name:String?,
        @SerializedName("password") val password:String?,
        @SerializedName("deviceToken") val deviceToken:String?
) {
}