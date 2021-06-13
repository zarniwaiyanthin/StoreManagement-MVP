package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class CustomerListResponse(
    @SerializedName("responseCode") val responseCode:String?,
    @SerializedName("responseMessage") val responseMessage:String?,
    @SerializedName("data") val data:List<Customer>?,
    @SerializedName("error") val error:List<ApiError>?
) {
}