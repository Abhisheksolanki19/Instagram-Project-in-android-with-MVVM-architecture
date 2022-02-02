package com.abhishekSolanki.instagramApp.ui.home.posts

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.abhishekSolanki.instagramApp.data.model.Post
import com.abhishekSolanki.instagramApp.ui.base.BaseAdapter

class PostsAdapter(
    parentLifecycle: Lifecycle,
    posts: ArrayList<Post>,
): BaseAdapter<Post, PostItemViewHolder>(parentLifecycle, posts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder =
        PostItemViewHolder(parent, this)

}