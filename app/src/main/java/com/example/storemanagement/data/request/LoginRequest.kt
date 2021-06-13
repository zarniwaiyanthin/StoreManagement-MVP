package com.example.storemanagement.data.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("name") val userName:String?,
    @SerializedName("password") val password:String?
) {
}