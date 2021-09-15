package br.com.hunterbay.data.repository.remote

import br.com.hunterbay.core.ErrorThrow
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.data.services.CreateAccountService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteRepositoryImp(
    private val createAccountService: CreateAccountService
): RemoteRepository {
    override suspend fun createAccount(userCreateAccount: UserCreateAccount) = flow {
        try {
            emit(createAccountService.createAccount(userCreateAccount))
        } catch (e: HttpException) {
            ErrorThrow.errorRequest(e.response()?.errorBody().toString())
        }
    }
}