package br.com.hunterbay.data.di

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataModule {
    private const val BASE_URL = "http://192.168.15.10:3000"
    private const val HTTP_TAG = "okHTTP"

    fun load() {
        loadKoinModules(networkModule())
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor { log ->
                    Log.e(HTTP_TAG, log)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory) {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create<T>()
    }
}