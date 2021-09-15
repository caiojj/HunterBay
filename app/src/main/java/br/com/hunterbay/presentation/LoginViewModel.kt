package br.com.hunterbay.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hunterbay.data.model.Login
import br.com.hunterbay.data.model.UserData
import br.com.hunterbay.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _stateLogin = MutableLiveData<State>()
    val stateLogin: LiveData<State>
    get() = _stateLogin

    fun login(use: Login) {
        viewModelScope.launch {
            loginUseCase(use)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _stateLogin.value = State.Loading
                }
                .catch {
                    _stateLogin.value = State.Error(it)
                }
                .collect {
                    _stateLogin.value = State.Logged(it)
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Logged(val body: Response<UserData>) : State()
        data class Error(val error: Throwable) : State()
    }
}