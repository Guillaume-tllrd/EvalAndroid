package com.example.evalandroid

import android.app.Application
import com.example.evalandroid.di.appModule
import com.example.evalandroid.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Classe principale de l'application, héritant de [Application].
 *
 * Cette classe agit comme le point d'entrée global du cycle de vie de l'application.
 * Elle est instanciée par le système Android avant tout autre composant (Activity, Service, Receiver).
 */
class EvalApplication  : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EvalApplication)
            modules(dataModule, appModule)
        }
    }
}