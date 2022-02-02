package com.abhishekSolanki.instagramApp.ui.photo

import android.os.Bundle
import android.view.View
import com.abhishekSolanki.instagramApp.R
import com.abhishekSolanki.instagramApp.di.component.FragmentComponent
import com.abhishekSolanki.instagramApp.ui.base.BaseFragment

class PhotoFragment: BaseFragment<PhotoViewModel>() {

    companion object {

        const val TAG = "PhotoFragment"

        fun newInstance(): PhotoFragment {
            val args = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_photo

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
    }
}