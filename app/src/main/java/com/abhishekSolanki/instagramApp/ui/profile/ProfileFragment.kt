package com.abhishekSolanki.instagramApp.ui.profile

import android.os.Bundle
import android.view.View
import com.abhishekSolanki.instagramApp.R
import com.abhishekSolanki.instagramApp.di.component.FragmentComponent
import com.abhishekSolanki.instagramApp.ui.base.BaseFragment
import com.abhishekSolanki.instagramApp.ui.photo.PhotoFragment

class ProfileFragment:BaseFragment<ProfileViewModel>() {

    companion object {

        const val TAG = "ProfileFragment"

        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_profile

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
    }
}