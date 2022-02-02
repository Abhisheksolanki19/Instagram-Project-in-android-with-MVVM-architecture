package com.abhishekSolanki.instagramApp.di.component

import com.abhishekSolanki.instagramApp.di.module.ApplicationTestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent:ApplicationComponent {
}