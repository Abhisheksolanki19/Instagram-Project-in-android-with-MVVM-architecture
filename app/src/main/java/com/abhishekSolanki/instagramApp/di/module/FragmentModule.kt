package com.abhishekSolanki.instagramApp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekSolanki.instagramApp.data.repository.PostRepository
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseFragment
import com.abhishekSolanki.instagramApp.ui.home.HomeViewModel
import com.abhishekSolanki.instagramApp.ui.home.posts.PostsAdapter
import com.abhishekSolanki.instagramApp.ui.photo.PhotoViewModel
import com.abhishekSolanki.instagramApp.ui.profile.ProfileViewModel
import com.abhishekSolanki.instagramApp.utils.ViewModelProviderFactory
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import java.io.File

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providePostsAdapter() = PostsAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        postRepository: PostRepository
    ): HomeViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(HomeViewModel::class) {
                HomeViewModel(
                    schedulerProvider, compositeDisposable, networkHelper, userRepository,
                    postRepository, ArrayList(), PublishProcessor.create()
                )
            }
        ).get(HomeViewModel::class.java)

    @Provides
    fun provideProfileViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
    ): ProfileViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(ProfileViewModel::class) {
                ProfileViewModel(
                    schedulerProvider, compositeDisposable, networkHelper,
                )
            }
        ).get(ProfileViewModel::class.java)

    @Provides
    fun providePhotoViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
    ): PhotoViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(PhotoViewModel::class) {
                PhotoViewModel(
                    schedulerProvider, compositeDisposable, networkHelper,
                )
            }
        ).get(PhotoViewModel::class.java)



}