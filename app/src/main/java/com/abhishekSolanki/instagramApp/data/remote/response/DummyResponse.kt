package com.abhishekSolanki.instagramApp.data.remote.response

import com.abhishekSolanki.instagramApp.data.model.Dummy
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DummyResponse(
    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("data")
    val data: List<Dummy>
)