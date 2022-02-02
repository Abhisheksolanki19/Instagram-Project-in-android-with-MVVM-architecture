package com.abhishekSolanki.instagramApp.ui.main

import androidx.lifecycle.MutableLiveData
import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.common.Event
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val profileNavigation = MutableLiveData<Event<Boolean>>()
    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val photoNavigation = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        homeNavigation.postValue(Event(true))
    }

    fun onProfileSelected() {
        profileNavigation.postValue(Event(true))
    }

    fun onHomeSelected() {
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoSelected() {
        photoNavigation.postValue(Event(true))
    }

}