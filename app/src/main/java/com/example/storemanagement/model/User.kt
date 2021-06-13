package com.example.storemanagement.model

import com.google.gson.annotations.SerializedName

class User(
        @SerializedName("userId") val userId:Int? = -1
) {
}