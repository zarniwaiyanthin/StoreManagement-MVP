package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class ApiError(
    @SerializedName("fieldCode") val fieldCode:Int?,
    @SerializedName("errorMessage") val errorMessage:String?
) {
}