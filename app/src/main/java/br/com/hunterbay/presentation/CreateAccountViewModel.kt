package br.com.hunterbay.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.domain.CreateAccountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

class CreateAccountViewModel(
    private val createAccountUseCase: CreateAccountUseCase
): ViewModel() {

    private val _statusUser = MutableLiveData<Status>()
    val statusUser: LiveData<Status>
    get() = _statusUser

    fun createAccount(userCreateAccount: UserCreateAccount) {
        viewModelScope.launch {
            createAccountUseCase(userCreateAccount)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _statusUser.value = Status.Loading
                }
                .catch {
                    _statusUser.value = Status.ErrorAccount(it)
                }
                .collect {
                    _statusUser.value = Status.Created(it)
                }
        }
    }

    sealed class Status {
        object Loading : Status()
        data class Created(val response: Response<Void>) : Status()
        data class ErrorAccount(val error: Throwable) : Status()
    }
}