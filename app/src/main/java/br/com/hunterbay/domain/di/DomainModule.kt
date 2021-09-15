package br.com.hunterbay.domain.di

import br.com.hunterbay.domain.CreateAccountUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModulesAccount())
    }

    private fun useCaseModulesAccount(): Module {
        return module {
            factory { CreateAccountUseCase(get()) }
        }
    }
}