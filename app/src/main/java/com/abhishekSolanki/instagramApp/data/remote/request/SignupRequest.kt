package com.abhishekSolanki.instagramApp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("password")
    val password: String,

    @Expose
    @SerializedName("name")
    val name: String
)