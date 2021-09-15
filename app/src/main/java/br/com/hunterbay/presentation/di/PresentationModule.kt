package br.com.hunterbay.presentation.di

import br.com.hunterbay.presentation.CreateAccountViewModel
import br.com.hunterbay.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { CreateAccountViewModel(get()) }
            viewModel { LoginViewModel(get()) }
        }
    }
}