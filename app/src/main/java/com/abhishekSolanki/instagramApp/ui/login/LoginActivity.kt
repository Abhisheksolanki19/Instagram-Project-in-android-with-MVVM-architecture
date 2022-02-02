package com.abhishekSolanki.instagramApp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.abhishekSolanki.instagramApp.R
import com.abhishekSolanki.instagramApp.di.component.ActivityComponent
import com.abhishekSolanki.instagramApp.ui.base.BaseActivity
import com.abhishekSolanki.instagramApp.ui.common.dialog.LoadingDialog
import com.abhishekSolanki.instagramApp.ui.main.MainActivity
import com.abhishekSolanki.instagramApp.ui.signup.SignupActivity
import com.abhishekSolanki.instagramApp.utils.common.Event
import com.abhishekSolanki.instagramApp.utils.common.Status
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity: BaseActivity<LoginViewModel>() {

    companion object{
        const val TAG = "LoginActivity"
    }

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun provideLayoutId(): Int = R.layout.activity_login

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onEmailChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onPasswordChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        bt_login.setOnClickListener { viewModel.onLogin() }

        tv_signup.setOnClickListener {
            viewModel.onSignUpClicked()
        }

        loadingDialog.apply {
            isCancelable = false
            arguments = Bundle().apply {
                putInt(LoadingDialog.MESSAGE_KEY, R.string.logging_in)
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        // Event is used by the view model to t
        // ell the activity to launch another activity
        // view model also provided the Bundle in the event that is needed for the Activity
        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })

        viewModel.launchSignup.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, SignupActivity::class.java))
                finish()
            }
        })

        viewModel.emailField.observe(this, Observer {
            if (etEmail.text.toString() != it) etEmail.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_email.error = it.data?.run { getString(this) }
                else -> layout_email.isErrorEnabled = false
            }
        })

        viewModel.passwordField.observe(this, Observer {
            if (et_password.text.toString() != it) etEmail.setText(it)
        })

        viewModel.passwordValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_password.error = it.data?.run { getString(this) }
                else -> layout_password.isErrorEnabled = false
            }
        })

        viewModel.loggingIn.observe(this, Observer {
            if (it)
                loadingDialog.show(supportFragmentManager, LoadingDialog.TAG)
            else try {
                loadingDialog.dismiss()
            } catch (e: NullPointerException) {
                // Sometime this happens
            }
        })
    }
}