package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class AddCustomerResponse(
        @SerializedName("responseCode") val responseCode:String?,
        @SerializedName("responseMessage") val responseMessage:String?,
        @SerializedName("data") val data:List<AddCustomer>?,
        @SerializedName("error") val error:List<ApiError>?
) {
}