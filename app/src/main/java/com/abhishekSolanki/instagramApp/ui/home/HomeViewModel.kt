package com.abhishekSolanki.instagramApp.ui.home

import androidx.lifecycle.MutableLiveData
import com.abhishekSolanki.instagramApp.data.model.Post
import com.abhishekSolanki.instagramApp.data.model.User
import com.abhishekSolanki.instagramApp.data.repository.PostRepository
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.common.Resource
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val allPostList: ArrayList<Post>,
    private val paginator: PublishProcessor<Pair<String?, String?>>
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val posts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()


    private val user: User =
        userRepository.getCurrentUser()!! // should not be used without logged in

    init {
        compositeDisposable.add(
            paginator
                .onBackpressureDrop()
                .doOnNext {
                    loading.postValue(true)
                }
                .concatMapSingle { pageIds ->
                    return@concatMapSingle postRepository
                        .fetchHomePostList(pageIds.first, pageIds.second, user)
                        .subscribeOn(Schedulers.io())
                        .doOnError {
                            loading.postValue(false)
                            handleNetworkError(it)
                        }
                }
                .subscribe(
                    {
                        allPostList.addAll(it)
                        loading.postValue(false)
                        posts.postValue(Resource.success(it))
                    },
                    {
                        loading.postValue(false)
                        handleNetworkError(it)
                    }
                )
        )
    }

    override fun onCreate() {
        loadMorePosts()
    }

    private fun loadMorePosts() {
        val firstPostId = if (allPostList.isNotEmpty()) allPostList[0].id else null
        val lastPostId = if (allPostList.size > 1) allPostList[allPostList.size-1].id else null
        if (checkInternetConnectionWithMessage()) paginator.onNext(Pair(firstPostId, lastPostId))
    }

    fun onLoadMore() {
        if (loading.value !== null && loading.value == false) loadMorePosts()
    }
}