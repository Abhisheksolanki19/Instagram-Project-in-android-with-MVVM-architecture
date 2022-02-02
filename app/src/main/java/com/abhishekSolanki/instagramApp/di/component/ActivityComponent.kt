package com.abhishekSolanki.instagramApp.di.component

import com.abhishekSolanki.instagramApp.di.ActivityScope
import com.abhishekSolanki.instagramApp.di.module.ActivityModule
import com.abhishekSolanki.instagramApp.ui.login.LoginActivity
import com.abhishekSolanki.instagramApp.ui.main.MainActivity
import com.abhishekSolanki.instagramApp.ui.signup.SignupActivity
import com.abhishekSolanki.instagramApp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: SignupActivity)

    fun inject(activity: MainActivity)

}