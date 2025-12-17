package com.example.evalandroid.di

import com.example.evalandroid.data.remote.provideHttpClient
import com.example.evalandroid.data.repository.PokemonRepositoryImpl
import com.example.evalandroid.domain.pokemon.PokemonRepository
import com.example.evalandroid.system.SoundManager
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext

import org.koin.dsl.module

/**
 * Module Koin qui fournit les dépendances de la couche Data.
 *
 */
val dataModule = module {

    single<HttpClient> { provideHttpClient() }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}