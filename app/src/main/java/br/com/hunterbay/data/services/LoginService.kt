package br.com.hunterbay.data.services

import br.com.hunterbay.data.model.Login
import br.com.hunterbay.data.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    suspend fun login(@Body user: Login) : Response<UserData>
}