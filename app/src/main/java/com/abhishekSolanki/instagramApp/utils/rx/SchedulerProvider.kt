package com.abhishekSolanki.instagramApp.utils.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}