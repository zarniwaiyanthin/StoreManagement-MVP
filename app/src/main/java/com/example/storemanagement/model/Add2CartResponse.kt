package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class Add2CartResponse(
        @SerializedName("responseCode") val responseCode:Int?,
        @SerializedName("responseMessage") val responseMessage:String?,
        @SerializedName("data") val data:CartData?,
        @SerializedName("error") val error:List<ApiError>?
) {
}