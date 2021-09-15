package br.com.hunterbay.data.model

data class UserCreateAccount(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
)