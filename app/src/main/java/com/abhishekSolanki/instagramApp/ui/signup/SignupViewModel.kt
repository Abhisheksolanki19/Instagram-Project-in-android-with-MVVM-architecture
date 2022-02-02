package com.abhishekSolanki.instagramApp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.abhishekSolanki.instagramApp.data.repository.UserRepository
import com.abhishekSolanki.instagramApp.ui.base.BaseViewModel
import com.abhishekSolanki.instagramApp.utils.common.*
import com.abhishekSolanki.instagramApp.utils.network.NetworkHelper
import com.abhishekSolanki.instagramApp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SignupViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    companion object {
        const val TAG = "SignupViewModel"
    }

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val nameField: MutableLiveData<String> = MutableLiveData()
    val signingUp: MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)
    val nameValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.NAME)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }


    override fun onCreate() {}

    fun onLoginClicked() = launchLogin.postValue(Event(emptyMap()))

    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    fun onNameChange(name: String) = nameField.postValue(name)

    fun onSignup() {
        val email = emailField.value
        val password = passwordField.value
        val name = nameField.value

        val validations = Validator.validateLoginFields(email, password, name)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null && name != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            Timber.d("successValidations: ${successValidation.size}")
            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                signingUp.postValue(true)
                compositeDisposable.add(
                    userRepository.doUserSignup(email, password, name)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                userRepository.saveCurrentUser(it)
                                signingUp.postValue(false)
                                launchMain.postValue(Event(emptyMap()))
                            },
                            {
                                handleNetworkError(it)
                                signingUp.postValue(false)
                            }
                        )
                )
            }
        }
    }
}