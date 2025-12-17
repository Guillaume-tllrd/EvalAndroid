package com.example.evalandroid.data.repository

import com.example.evalandroid.data.dto.PokemonDto
import com.example.evalandroid.data.mapper.toDomain
import com.example.evalandroid.domain.pokemon.Pokemon
import com.example.evalandroid.domain.pokemon.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Implémentation concrète de l'interface [PokemonRepository].
 *
 * Cette classe a pour responsabilités :
 * 1. L'exécution des requêtes HTTP via le client [HttpClient] (Ktor).
 * 2. La transformation des données techniques (DTO) vers les entités du domaine via des mappers.
 *
 * @property client Le client HTTP configuré pour communiquer avec l'API Pokémon.
 */
class PokemonRepositoryImpl(private val client: HttpClient): PokemonRepository{
    override suspend fun getPokemons(): List<Pokemon> {
        return try {
            val dto = client.get("pokemon/limit/20").body<List<PokemonDto>>()
            dto.map { it.toDomain() }
        }catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getPokemonById(pokemonId: Int): Pokemon? {
        return try {
            val dto = client.get("pokemon/$pokemonId").body<PokemonDto>()
            dto.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}