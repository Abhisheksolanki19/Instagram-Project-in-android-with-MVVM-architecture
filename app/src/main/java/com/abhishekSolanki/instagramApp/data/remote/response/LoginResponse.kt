package com.abhishekSolanki.instagramApp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("status")
    var status: Int,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("accessToken")
    var accessToken: String,

    @Expose
    @SerializedName("userId")
    var userId: String,

    @Expose
    @SerializedName("userName")
    var userName: String,

    @Expose
    @SerializedName("userEmail")
    var userEmail: String,

    @Expose
    @SerializedName("profilePic")
    var profilePicUrl: String?

)