package br.com.hunterbay.domain

import br.com.hunterbay.core.UseCase
import br.com.hunterbay.data.model.Login
import br.com.hunterbay.data.model.UserData
import br.com.hunterbay.data.repository.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class LoginUseCase(
    private val remoteRepository: RemoteRepository
): UseCase.NoSource<Login, Response<UserData>>() {
    override suspend fun execute(paramOne: Login): Flow<Response<UserData>> {
        return remoteRepository.login(paramOne)
    }
}