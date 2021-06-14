package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class RegisterResponse(
        @SerializedName("responseCode") val responseCode:Int?,
        @SerializedName("responseMessage") val responseMessage:String?,
        @SerializedName("data") val data: User,
        @SerializedName("error") val error:List<ApiError>?
) {
}