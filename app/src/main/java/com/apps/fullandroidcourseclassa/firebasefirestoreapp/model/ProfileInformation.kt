package com.apps.fullandroidcourseclassa.firebasefirestoreapp.model

data class ProfileInformation(
    var fullName: String = "",
    var userName: String = "",
    var emailAddress: String = "",
    var phoneNumber: Int = 0,
    var password: String = "",
) {
}