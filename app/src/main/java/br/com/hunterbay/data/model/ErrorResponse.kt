package br.com.hunterbay.data.model

data class ErrorResponse(
    val status: String,
    val code: String,
    val message: String
)
