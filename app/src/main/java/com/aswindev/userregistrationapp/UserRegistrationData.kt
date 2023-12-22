package com.aswindev.userregistrationapp

import java.io.Serializable

data class UserRegistrationData(
    val title: String?,
    val firstName: String,
    val lastName: String,
    val emailId: String,
    val phoneNumber: String,
    val password: String
) : Serializable {
    fun getFullName() : String = "$title $firstName $lastName"
}