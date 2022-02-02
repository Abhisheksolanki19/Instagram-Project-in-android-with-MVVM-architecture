package com.abhishekSolanki.instagramApp.di.component


import com.abhishekSolanki.instagramApp.di.FragmentScope
import com.abhishekSolanki.instagramApp.di.module.FragmentModule
import com.abhishekSolanki.instagramApp.ui.home.HomeFragment
import com.abhishekSolanki.instagramApp.ui.photo.PhotoFragment
import com.abhishekSolanki.instagramApp.ui.profile.ProfileFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: PhotoFragment)

}