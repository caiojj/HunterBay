package br.com.hunterbay.data.model

data class UserData(
    val id: String,
    val createat: String,
    val email: String,
    val lastname: String,
    val name: String,
    val password: String,
    val uriprofile: String?
)
