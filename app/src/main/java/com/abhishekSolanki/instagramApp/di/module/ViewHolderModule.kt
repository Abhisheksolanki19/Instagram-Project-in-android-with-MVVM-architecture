package com.abhishekSolanki.instagramApp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.abhishekSolanki.instagramApp.di.ViewModelScope
import com.abhishekSolanki.instagramApp.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}