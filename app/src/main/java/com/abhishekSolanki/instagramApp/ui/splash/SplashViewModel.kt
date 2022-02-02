package com.abhishekSolanki.instagramApp.ui.splash

import androidx.lifecycle.MutableLiveData
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.common.Event
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
//        // Empty Bundle passed to Activity in Event that is needed by the other Activity
//        // Here in actual application we will decide which screen to open based on
//        // either the user is logged in or not
//        launchDummy.postValue(Event(emptyMap()))

        // Empty map of key and serialized value is passed to Activity in Event that is needed by the other Activity
        if (userRepository.getCurrentUser() != null)
            launchMain.postValue(Event(emptyMap()))
        else
            launchLogin.postValue(Event(emptyMap()))
    }
}