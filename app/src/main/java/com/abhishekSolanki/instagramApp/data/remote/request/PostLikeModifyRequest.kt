package com.abhishekSolanki.instagramApp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostLikeModifyRequest(
    @Expose
    @SerializedName("postId")
    var postId: String
)