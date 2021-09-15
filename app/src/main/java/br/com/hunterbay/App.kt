package br.com.hunterbay

import android.app.Application
import br.com.hunterbay.data.di.DataModule
import br.com.hunterbay.domain.di.DomainModule
import br.com.hunterbay.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@App) }
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}