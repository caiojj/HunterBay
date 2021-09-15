package br.com.hunterbay.domain

import br.com.hunterbay.core.UseCase
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.data.repository.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CreateAccountUseCase(
    private val remoteRepository: RemoteRepository
): UseCase.NoSource<UserCreateAccount, Response<Void>>() {
    override suspend fun execute(paramOne: UserCreateAccount): Flow<Response<Void>> {
        return remoteRepository.createAccount(paramOne)
    }
}