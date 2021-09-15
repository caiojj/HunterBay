package br.com.hunterbay.data.repository.remote

import br.com.hunterbay.core.ErrorThrow
import br.com.hunterbay.data.model.Login
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.data.services.CreateAccountService
import br.com.hunterbay.data.services.LoginService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteRepositoryImp(
    private val createAccountService: CreateAccountService,
    private val loginService: LoginService
): RemoteRepository {
    override suspend fun createAccount(userCreateAccount: UserCreateAccount) = flow {
        try {
            emit(createAccountService.createAccount(userCreateAccount))
        } catch (e: HttpException) {
            ErrorThrow.errorRequest(e.response()?.errorBody().toString())
        }
    }

    override suspend fun login(user: Login) = flow {
        try {
            emit(loginService.login(user))
        } catch (e: HttpException) {
            ErrorThrow.errorRequest(e.response()?.errorBody().toString())
        }
    }
}