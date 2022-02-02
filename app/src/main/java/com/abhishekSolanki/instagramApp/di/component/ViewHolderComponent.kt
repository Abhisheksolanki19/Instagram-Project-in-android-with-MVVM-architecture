package com.abhishekSolanki.instagramApp.di.component


import com.abhishekSolanki.instagramApp.di.ViewModelScope
import com.abhishekSolanki.instagramApp.di.module.ViewHolderModule
import com.abhishekSolanki.instagramApp.ui.home.posts.PostItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: PostItemViewHolder)
}