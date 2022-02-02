package com.abhishekSolanki.instagramApp.utils.common

import com.abhishekSolanki.instagramApp.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class ValidatorTest {

    @Test
    fun givenValidEmailAndValidPassword_whenValidate_shouldReturnSuccess(){
        val email = "test@gmail.com"
        val password = "password"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenInValidEmailAndValidPassword_whenValidate_shouldReturnEmailError(){
        val email = "test"
        val password = "password"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenValidEmailAndInValidPassword_whenValidate_shouldReturnPasswordError(){
        val email = "test@gmail.com"
        val password = "pwd"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length))
            )
        )
    }

}