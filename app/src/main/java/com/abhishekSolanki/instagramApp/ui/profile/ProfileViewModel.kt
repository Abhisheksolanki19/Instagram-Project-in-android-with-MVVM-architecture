package com.abhishekSolanki.instagramApp.ui.profile

import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ProfileViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
}