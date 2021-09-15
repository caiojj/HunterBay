package br.com.hunterbay.data.repository.remote

import br.com.hunterbay.data.model.Login
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.data.model.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteRepository {
    suspend fun createAccount(userCreateAccount: UserCreateAccount) : Flow<Response<Void>>
    suspend fun login(user: Login) : Flow<Response<UserData>>
}