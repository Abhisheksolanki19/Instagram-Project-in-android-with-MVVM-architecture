package com.abhishekSolanki.instagramApp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseActivity
import com.abhishekSolanki.instagramApp.ui.common.dialog.LoadingDialog
import com.abhishekSolanki.instagramApp.ui.login.LoginViewModel
import com.abhishekSolanki.instagramApp.ui.main.MainViewModel
import com.abhishekSolanki.instagramApp.ui.signup.SignupViewModel
import com.abhishekSolanki.instagramApp.ui.splash.SplashViewModel
import com.abhishekSolanki.instagramApp.utils.ViewModelProviderFactory
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideLoadingDialog() = LoadingDialog()

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)

    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(LoginViewModel::class.java)

    @Provides
    fun provideSignupViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SignupViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SignupViewModel::class) {
            SignupViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(SignupViewModel::class.java)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(MainViewModel::class.java)


}