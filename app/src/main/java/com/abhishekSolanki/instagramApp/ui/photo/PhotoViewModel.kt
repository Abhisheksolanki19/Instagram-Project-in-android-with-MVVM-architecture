package com.abhishekSolanki.instagramApp.ui.photo

import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PhotoViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
}