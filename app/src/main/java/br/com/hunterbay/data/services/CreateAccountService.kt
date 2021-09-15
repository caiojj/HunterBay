package br.com.hunterbay.data.services

import br.com.hunterbay.data.model.UserCreateAccount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateAccountService {
    @POST("/createAccount")
    suspend fun createAccount(@Body userCreateAccount: UserCreateAccount) : Response<Void>
}