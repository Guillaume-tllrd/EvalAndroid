package com.example.evalandroid.di

import com.example.evalandroid.system.SoundManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
* Module Koin dédié aux dépendances transverses et aux services système.
*
*/
val appModule = module {

    single { SoundManager(androidContext()) }

}