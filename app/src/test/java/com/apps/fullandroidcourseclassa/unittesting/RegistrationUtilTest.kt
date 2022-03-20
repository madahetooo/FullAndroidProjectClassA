package com.apps.fullandroidcourseclassa.unittesting


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{
    @Test
    fun `empty username returns false`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `valid username and confirmpassword and password is matched`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Malak",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `username is already taken`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Amr",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password and confirm password not matched`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Riham",
            "123",
            "4243"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password is not empty`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "AhmedHosny",
            "",
            ""
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password has less than 2 digits`(){
        var result = RegistrationUtil.validateRegistrationInput(
            "Salaman",
            "eslam1",
            "eslam1"
        )
        assertThat(result).isFalse()

    }
}