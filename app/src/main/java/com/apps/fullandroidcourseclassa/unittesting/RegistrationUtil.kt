package com.apps.fullandroidcourseclassa.unittesting

object RegistrationUtil {

    private val existingUsers = listOf("Eslam","Amr","Ahmed")
    // username may be empty
    // username is already taken
    // password is not complex
    // confirmPassword not match password
    // password is empty
    fun validateRegistrationInput(
        username:String,
        password:String,
        confirmPassword:String
    ):Boolean{
        if(username.isEmpty()|| password.isEmpty()){
            return false
        }
        if(username in existingUsers ){
            return false
        }
        if (password != confirmPassword){
            return false
        }
        if (password.count { it.isDigit() } < 2){
            return false
        }
        return true
    }
}