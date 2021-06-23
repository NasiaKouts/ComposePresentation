package com.example.composepresentationsample.model

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val number: String
) {
    val initials = "${firstName.first()}${lastName.first()}"
}