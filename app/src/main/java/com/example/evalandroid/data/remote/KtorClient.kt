package com.example.evalandroid.data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Fournit et configure l'instance du client HTTP (Ktor) utilisée par l'application.
 *
 * @return Une instance de [HttpClient] prête à être injectée et utilisée.
 */
fun provideHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        defaultRequest {
            url("https://pokebuildapi.fr/api/v1/")
        }
    }
}