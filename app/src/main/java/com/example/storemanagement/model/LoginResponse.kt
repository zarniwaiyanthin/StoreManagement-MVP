package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("responseCode") val responseCode:String?,
    @SerializedName("responseMessage") val responseMessage:String?,
    @SerializedName("data") val data:User?,
    @SerializedName("error") val error:List<ApiError>?
) {
}