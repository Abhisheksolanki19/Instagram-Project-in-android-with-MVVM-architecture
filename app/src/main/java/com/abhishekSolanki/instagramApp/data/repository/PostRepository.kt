package com.abhishekSolanki.instagramApp.data.repository

import com.abhishekSolanki.instagramApp.data.local.db.DatabaseService
import com.abhishekSolanki.instagramApp.data.model.Post
import com.abhishekSolanki.instagramApp.data.model.User
import com.abhishekSolanki.instagramApp.data.remote.NetworkService
import com.abhishekSolanki.instagramApp.data.remote.request.PostLikeModifyRequest
import io.reactivex.Single
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchHomePostList(
        firstPostId: String?,
        lastPostId: String?,
        user: User
    ): Single<List<Post>> {
        return networkService.doHomePostListCall(
            firstPostId,
            lastPostId,
            user.id,
            user.accessToken
        ).map { it.data }
    }


    fun makeLikePost(post: Post, user: User): Single<Post> {
        return networkService.doPostLikeCall(
            PostLikeModifyRequest(post.id),
            user.id,
            user.accessToken
        ).map {
            post.likedBy?.apply {
                this.find { postUser -> postUser.id == user.id } ?: this.add(
                    Post.User(
                        user.id,
                        user.name,
                        user.profilePicUrl
                    )
                )
            }
            return@map post
        }
    }

    fun makeUnlikePost(post: Post, user: User): Single<Post> {
        return networkService.doPostUnlikeCall(
            PostLikeModifyRequest(post.id),
            user.id,
            user.accessToken
        ).map {
            post.likedBy?.apply {
                this.find { postUser -> postUser.id == user.id }?.let { this.remove(it) }
            }
            return@map post
        }
    }

}