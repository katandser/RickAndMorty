package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.api.IServerApi
import com.example.rickandmorty.api.ServerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {

    private val appModule = module {
        single { ServerRepository(IServerApi.create()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}