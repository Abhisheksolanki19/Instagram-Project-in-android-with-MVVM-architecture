package com.abhishekSolanki.instagramApp.ui.home.posts

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.abhishekSolanki.instagramApp.R
import com.abhishekSolanki.instagramApp.data.model.Post
import com.abhishekSolanki.instagramApp.di.component.ViewHolderComponent
import com.abhishekSolanki.instagramApp.ui.base.BaseItemViewHolder
import com.abhishekSolanki.instagramApp.utils.common.GlideHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_view_post.view.*

class PostItemViewHolder(
    parent: ViewGroup,
    private val adapter: PostsAdapter
) : BaseItemViewHolder<Post, PostItemViewModel>(R.layout.item_view_post, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) =
        viewHolderComponent.inject(this)

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tvName.text = it
        })

        viewModel.postTime.observe(this, Observer {
            itemView.tvTime.text = it
        })

        viewModel.likesCount.observe(this, Observer {
            itemView.tvLikesCount.text = itemView.context.getString(R.string.post_like_label, it)
        })

        viewModel.isLiked.observe(this, Observer {
            if (it) itemView.ivLike.setImageResource(R.drawable.ic_heart_selected)
            else itemView.ivLike.setImageResource(R.drawable.ic_heart_unselected)
        })

        viewModel.profileImage.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(itemView.ivProfile.context)
                    .load(GlideHelper.getProtectedUrl(url, headers))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))

                if (placeholderWidth > 0 && placeholderHeight > 0) {
                    val params = itemView.ivProfile.layoutParams as ViewGroup.LayoutParams
                    params.width = placeholderWidth
                    params.height = placeholderHeight
                    itemView.ivProfile.layoutParams = params
                    glideRequest
                        .apply(RequestOptions.overrideOf(placeholderWidth, placeholderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_unselected))
                }
                glideRequest.into(itemView.ivProfile)
            }
        })

        viewModel.imageDetail.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(itemView.ivPost.context)
                    .load(GlideHelper.getProtectedUrl(url, headers))

                if (placeholderWidth > 0 && placeholderHeight > 0) {
                    val params = itemView.ivPost.layoutParams as ViewGroup.LayoutParams
                    params.width = placeholderWidth
                    params.height = placeholderHeight
                    itemView.ivPost.layoutParams = params
                    glideRequest
                        .apply(RequestOptions.overrideOf(placeholderWidth, placeholderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_photo))
                }
                glideRequest.into(itemView.ivPost)
            }
        })
    }

    override fun setupView(view: View) {
        itemView.ivLike.setOnClickListener { viewModel.onLikeClick() }
    }
}