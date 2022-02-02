package com.abhishekSolanki.instagramApp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose
    @SerializedName("email")
    var email: String,

    @Expose
    @SerializedName("password")
    var password: String
)