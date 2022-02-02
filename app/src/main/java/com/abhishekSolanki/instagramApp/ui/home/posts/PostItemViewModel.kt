package com.abhishekSolanki.instagramApp.ui.home.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.abhishekSolanki.instagramApp.R
import com.abhishekSolanki.instagramApp.data.model.Image
import com.abhishekSolanki.instagramApp.data.model.Post
import com.abhishekSolanki.instagramApp.data.remote.Networking
import com.abhishekSolanki.instagramApp.data.repository.PostRepository
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseItemViewModel
import com.abhishekSolanki.instagramApp.utils.common.Event
import com.abhishekSolanki.instagramApp.utils.common.Resource
import com.abhishekSolanki.instagramApp.utils.common.TimeUtils
import com.abhishekSolanki.instagramApp.utils.display.ScreenUtils
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    private val postRepository: PostRepository
): BaseItemViewModel<Post>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "PostItemViewModel"
    }

    private val user = userRepository.getCurrentUser()!!
    private val screenWidth = ScreenUtils.getScreenWidth()
    private val screenHeight = ScreenUtils.getScreenHeight()
    private val headers = mapOf(
        Pair(Networking.HEADER_API_KEY, Networking.API_KEY),
        Pair(Networking.HEADER_USER_ID, user.id),
        Pair(Networking.HEADER_ACCESS_TOKEN, user.accessToken)
    )

    val postDeleted: MutableLiveData<Event<Post>> = MutableLiveData()
    val likeClicked: MutableLiveData<Event<Post>> = MutableLiveData()
    val likesCountClicked: MutableLiveData<Event<Post>> = MutableLiveData()
    val isOwner: LiveData<Boolean> = Transformations.map(data) { it.creator.id == user.id}
    val name: LiveData<String> = Transformations.map(data) { it.creator.name }
    val postTime: LiveData<String> = Transformations.map(data) { TimeUtils.getTimeAgo(it.createdAt) }
    val likesCount: LiveData<Int> = Transformations.map(data) { it.likedBy?.size ?: 0 }
    val isLiked: LiveData<Boolean> = Transformations.map(data) {
        it.likedBy?.find { postUser -> postUser.id == user.id } !== null
    }

    val profileImage: LiveData<Image> = Transformations.map(data) {
        it.creator.profilePicUrl?.run { Image(this, headers) }
    }

    val imageDetail: LiveData<Image> = Transformations.map(data) {
        Image(
            it.imageUrl,
            headers,
            screenWidth,
            it.imageHeight?.let { height ->
                return@let (calculateScaleFactor(it) * height).toInt()
            } ?: screenHeight / 3)
    }

    override fun onCreate() {}

    private fun calculateScaleFactor(post: Post) =
        post.imageWidth?.let { return@let screenWidth.toFloat() / it } ?: 1f


    fun onLikeClick() = data.value?.let {
        if (networkHelper.isNetworkConnected()) {
            val api =
                if (isLiked.value == true)
                    postRepository.makeUnlikePost(it, user)
                else
                    postRepository.makeLikePost(it, user)

            compositeDisposable.add(api
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    { responsePost ->
                        if (responsePost.id == it.id) {
                            likeClicked.postValue(Event(data.value!!))
                            updateData(responsePost)
                        }
                    },
                    { error -> handleNetworkError(error) }
                )
            )
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }
    }

    fun onLikesCountClick() = likesCountClicked.postValue(Event(data.value!!))
}